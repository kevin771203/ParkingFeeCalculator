package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class ParkingFeeCalculator {


    private final Duration THIRTY_MINUTES = Duration.ofMinutes(30L);
    private final Duration FIFTY_MINUTES = Duration.ofMinutes(15L);

    public long calculate(ParkingSession parkingSession) {

        Duration duration = parkingSession.getTotalDuration();

        if (isShort(duration)) {
            return 0L;
        }


        List<DailySession> dailySessions = parkingSession.getDailySessions();

        long totalFee = 0L;
        for (DailySession dailySession : dailySessions) {

            long todayFee = getRegularFee(dailySession.getTodayDuration() ,dailySession.getToday());
            totalFee += Math.min(todayFee, 150L);

        }


        return totalFee;


    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTY_MINUTES) <= 0;
    }

    private long getRegularFee(Duration duration, LocalDate today) {

        long periods = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();

        int unitPrice = DayOfWeek.SATURDAY.equals(today.getDayOfWeek())
                ? 50
                : 30;

        return periods * unitPrice;

    }

}