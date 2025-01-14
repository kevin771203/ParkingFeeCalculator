package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class HolidayBook {
    static boolean isHoliday(LocalDate today) {
        return List.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY).contains(today.getDayOfWeek());
    }
}