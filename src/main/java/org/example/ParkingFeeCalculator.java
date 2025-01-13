package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingFeeCalculator {



    public long calculate(LocalDateTime start, LocalDateTime end) {

        long minBetween = ChronoUnit.MINUTES.between(start, end);

        if (minBetween < 15) {
            return 0L;
        }

        long regularFee = getRegularFee(minBetween);

        return Math.min(regularFee,150L);

    }

    private long getRegularFee(long minBetween) {
        long periods = minBetween / 30;

        return (periods + 1) * 30;
    }
}