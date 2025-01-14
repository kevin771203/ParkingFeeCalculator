package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HolidayBook {

    static boolean isHoliday(LocalDate today) {

        Set<LocalDate> nationalHolidays = new HashSet<>();
        nationalHolidays.add(LocalDate.of(2025, 1, 1));



        return nationalHolidays.contains(today) ||
                List.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY).contains(today.getDayOfWeek());
    }
}