package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingFeeCalculator {



    public long calculate(LocalDateTime start, LocalDateTime end) {

        long minBetween = ChronoUnit.MINUTES.between(start, end);

        if (minBetween < 15) {
            return 0L;
        }

        long periods = minBetween / 30;

        return (periods + 1) * 30;
//
//        if (minBetween < 30) {
//            return 30L;
//        }
//
//        if (minBetween < 60) {
//            return 60L;
//        }
//
//        return 90L;
    }
}