package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingFeeCalculator {


    private final Duration THIRTY_MINUTES = Duration.ofMinutes(30L);
    private final Duration FIFTY_MINUTES = Duration.ofMinutes(15L);

    public long calculate(ParkingSession parkingSession) {

        Duration duration = Duration.between(parkingSession.getStart(), parkingSession.getEnd());

        if (isShort(duration)) {
            return 0L;
        }


        List<Duration> dailyDurations = getDailyDurations(parkingSession);

        long totalFee = 0L;
        for (Duration dailyDuration : dailyDurations) {

            long todayFee = getRegularFee(dailyDuration);
            totalFee += Math.min(todayFee, 150L);

        }

        return totalFee;


    }

    private static List<Duration> getDailyDurations(ParkingSession parkingSession) {
        List<Duration> dailyDurations = new ArrayList<>();
        LocalDateTime todayStart = parkingSession.getStart().toLocalDate().atStartOfDay();
        while (todayStart.isBefore(parkingSession.getEnd())) {


            LocalDateTime tomorrowStart = todayStart.plusDays(1L);


            LocalDateTime todaySessionStart = parkingSession.getStart().isAfter(todayStart)
                    ? parkingSession.getStart()
                    : todayStart;

            LocalDateTime todaySessionEnd = parkingSession.getEnd().isBefore(tomorrowStart)
                    ? parkingSession.getEnd()
                    : tomorrowStart;

            Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);
            dailyDurations.add(todayDuration);


            todayStart = tomorrowStart;

        }
        return dailyDurations;
    }

    private long getRegularFee(Duration duration) {

        long periods = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();
        long fee = periods * 30;
        return fee;

    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTY_MINUTES) <= 0;
    }
}