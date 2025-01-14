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

    public long calculate(LocalDateTime start, LocalDateTime end) {

        Duration duration = Duration.between(start, end);

        if (isShort(duration)) {
            return 0L;
        }


        long totalFee = 0L;

        List<Duration> dailyDurations = new ArrayList<>();
        LocalDateTime todayStart = start.toLocalDate().atStartOfDay();
        while (todayStart.isBefore(end)) {


            LocalDateTime tomorrowStart = todayStart.plusDays(1L);


            LocalDateTime todaySessionStart = start.isAfter(todayStart)
                    ? start
                    : todayStart;

            LocalDateTime todaySessionEnd = end.isBefore(tomorrowStart)
                    ? end
                    : tomorrowStart;

            Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);
            dailyDurations.add(todayDuration);

//            long todayFee = getRegularFee(todayDuration);
//            totalFee += Math.min(todayFee, 150L);


            todayStart = tomorrowStart;

        }

        for (Duration dailyDuration : dailyDurations) {

            long todayFee = getRegularFee(dailyDuration);
            totalFee += Math.min(todayFee, 150L);

        }

        return totalFee;


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