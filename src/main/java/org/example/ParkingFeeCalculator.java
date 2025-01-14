package org.example;

import java.time.Duration;
import java.util.List;

public class ParkingFeeCalculator {

    private final HolidayBook holidayBook;
    private Duration FIFTY_MINUTES = Duration.ofMinutes(15L);

    public ParkingFeeCalculator() {
        holidayBook = new HolidayBook();
    }

    public long calculate(ParkingSession parkingSession) {

        Duration duration = parkingSession.getTotalDuration();

        if (isShort(duration)) {
            return 0L;
        }


        List<DailySession> dailySessions = parkingSession.getDailySessions();

        long totalFee = 0L;
        for (DailySession dailySession : dailySessions) {

            long dailyFee = holidayBook.getDailyFee(dailySession);

            totalFee += dailyFee;

        }


        return totalFee;


    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTY_MINUTES) <= 0;
    }

}