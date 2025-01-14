package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HolidayBook {

    private Set<LocalDate> nationalHolidays = new HashSet<>();

    public HolidayBook() {
        nationalHolidays.add(LocalDate.of(2025, 1, 1));
    }

    boolean isHoliday(LocalDate today) {




        return nationalHolidays.contains(today) ||
                List.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY).contains(today.getDayOfWeek());
    }
}