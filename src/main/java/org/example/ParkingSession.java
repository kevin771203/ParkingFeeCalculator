package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public final class ParkingSession {

    private final LocalDateTime start;
    private final LocalDateTime end;


    List<DailySession> getDailyDurations() {

        List<Duration> dailyDurations = new ArrayList<>();
        List<DailySession>  dailySessions = new ArrayList<>();

        LocalDateTime todayStart = getStart().toLocalDate().atStartOfDay();
        while (todayStart.isBefore(getEnd())) {


            LocalDateTime tomorrowStart = todayStart.plusDays(1L);


            LocalDateTime todaySessionStart = getStart().isAfter(todayStart)
                    ? getStart()
                    : todayStart;

            LocalDateTime todaySessionEnd = getEnd().isBefore(tomorrowStart)
                    ? getEnd()
                    : tomorrowStart;

            Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);
            dailyDurations.add(todayDuration);

            dailySessions.add(new DailySession(
                    todayDuration
            ));


            todayStart = tomorrowStart;

        }

        return dailySessions;
    }

    Duration getTotalDuration() {
        return Duration.between(getStart(), getEnd());
    }
}