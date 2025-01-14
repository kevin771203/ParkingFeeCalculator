package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingFeeCalculator {


    private final Duration THIRTY_MINUTES = Duration.ofMinutes(30L);
    private final Duration FIFTY_MINUTES = Duration.ofMinutes(15L);

    public long calculate(LocalDateTime start, LocalDateTime end) {

        Duration duration = Duration.between(start, end);

        if (isShort(duration)) {
            return 0L;
        }

//        if (start.toLocalDate().equals(end.toLocalDate())) {
//
//
//            long fee = getRegularFee(duration);
//
//            return Math.min(fee, 150L);

        //} else{

        LocalDateTime todayStart = start.toLocalDate().atStartOfDay();

        long totalFee = 0L;

        while (todayStart.isBefore(end)) {

            if (start.isAfter(todayStart)
                    && !end.isBefore(todayStart.plusDays(1L))) {

                LocalDateTime todaySessionStart = start;
                LocalDateTime todaySessionEnd = todayStart.plusDays(1L);

                Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);

                long todayFee = getRegularFee(todayDuration);

                totalFee += Math.min(todayFee, 150L);

            } else if (!start.isAfter(todayStart)
                    && end.isBefore(todayStart.plusDays(1L))) {

                LocalDateTime todaySessionStart = todayStart;
                LocalDateTime todaySessionEnd = end;

                Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);

                long todayFee = getRegularFee(todayDuration);

                totalFee += Math.min(todayFee, 150L);

            } else if (start.isAfter(todayStart)
                    && end.isBefore(todayStart.plusDays(1L))) {

                LocalDateTime todaySessionStart = start;
                LocalDateTime todaySessionEnd = end;

                Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);

                long todayFee = getRegularFee(todayDuration);

                totalFee += Math.min(todayFee, 150L);

            } else {

                totalFee += 150L;
            }

            todayStart = todayStart.plusDays(1L);

        }

        return totalFee;

        //}

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