package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;

public class ParkingFeeCalculator {

    private final HolidayBook holidayBook = new HolidayBook();
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

            long dailyLimit = HolidayBook.isHoliday(dailySession.getToday())
                    ? 2400
                    : 150;

            totalFee += Math.min(todayFee, dailyLimit);

        }


        return totalFee;


    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTY_MINUTES) <= 0;
    }

    private long getRegularFee(DailySession dailySession) {

        long periods = BigDecimal.valueOf(dailySession.getTodayDuration().toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();

        return periods * (holidayBook.isHoliday(dailySession.getToday())
                ? 50
                : 30);

    }

}