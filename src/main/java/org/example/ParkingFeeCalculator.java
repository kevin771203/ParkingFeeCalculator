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

            long todayFee = getRegularFee(dailySession);

            long dailyLimit = isHoliday(dailySession.getToday())
                    ? 2400
                    : 150;

            totalFee += Math.min(todayFee, dailyLimit);

        }


        return totalFee;


    }

    private static boolean isHoliday(LocalDate today) {
        return List.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY).contains(today.getDayOfWeek());
    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTY_MINUTES) <= 0;
    }

    private long getRegularFee(DailySession dailySession) {

        long periods = BigDecimal.valueOf(dailySession.getTodayDuration().toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();

        return periods * (isHoliday(dailySession.getToday())
                ? 50
                : 30);

    }

}