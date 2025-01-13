package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingFeeCalculator {


    public long calculate(LocalDateTime start, LocalDateTime end) {

        Duration duration = Duration.between(start, end);

        if (duration.compareTo(Duration.ofMinutes(15L)) <= 0) {
            return 0;
        }

        Duration thirtyMinutes = Duration.ofMinutes(30L);

        long periods = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(thirtyMinutes.toNanos()), RoundingMode.UP)
                .longValue();
        long fee = periods * 30;

        return Math.min(fee, 150L);

//        long minBetween = ChronoUnit.MINUTES.between(start, end);
//
//        if (minBetween <= 15) {
//            return 0L;
//        }
//
//        long regularFee = getRegularFee(minBetween);
//
//
//
//    }

//    private long getRegularFee(long minBetween) {
//        long periods = minBetween / 30;
//
//        return (periods + 1) * 30;
//    }
    }
}