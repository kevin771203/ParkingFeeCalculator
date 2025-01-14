package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;

public class ParkingFeeCalculator {


    private final Duration THIRTY_MINUTES = Duration.ofMinutes(30L);
    private final Duration FIFTY_MINUTES = Duration.ofMinutes(15L);

    public long calculate(ParkingSession parkingSession) {

        Duration duration = parkingSession.getTotalDuration();

        if (isShort(duration)) {
            return 0L;
        }


        List<DailySession> dailyDurations = parkingSession.getDailyDurations();

        long totalFee = 0L;
        for (DailySession dailySession : dailyDurations) {

            long todayFee = getRegularFee(dailySession.getTodayDuration());
            totalFee += Math.min(todayFee, 150L);

        }


        return totalFee;


    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTY_MINUTES) <= 0;
    }

    private long getRegularFee(Duration duration) {

        long periods = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();

        int unitPrice = 30;
        long fee = periods * unitPrice;
        return fee;

    }

}