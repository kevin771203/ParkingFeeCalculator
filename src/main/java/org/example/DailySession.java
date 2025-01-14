package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;

@Data
@AllArgsConstructor
public class DailySession {
    private final Duration todayDuration;


}
