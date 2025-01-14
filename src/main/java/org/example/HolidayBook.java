package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HolidayBook {

    private Duration THIRTY_MINUTES = Duration.ofMinutes(30L);

    private Set<LocalDate> nationalHolidays = new HashSet<>();

    public HolidayBook() {
        nationalHolidays.add(LocalDate.of(2025, 1, 1));
    }

    boolean isHoliday(LocalDate today) {


        return nationalHolidays.contains(today) ||
                List.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY).contains(today.getDayOfWeek());
    }

    long getDailyFee(DailySession dailySession, ParkingFeeCalculator parkingFeeCalculator) {
        long todayFee = getRegularFee(dailySession, parkingFeeCalculator);

        long dailyLimit = isHoliday(dailySession.getToday())
                ? 2400
                : 150;

        return Math.min(todayFee, dailyLimit);
    }

    private long getRegularFee(DailySession dailySession, ParkingFeeCalculator parkingFeeCalculator) {

        long periods = BigDecimal.valueOf(dailySession.getTodayDuration().toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();

        return periods * (isHoliday(dailySession.getToday())
                ? 50
                : 30);

    }
}