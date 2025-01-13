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

        if (duration.compareTo(FIFTY_MINUTES) <= 0) {
            return 0;
        }

        long periods = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();
        long fee = periods * 30;

        return Math.min(fee, 150L);

    }
}