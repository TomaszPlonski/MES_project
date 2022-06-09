package com.github.tomaszplonski.mes_project.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class WorkingDays {

    /**
     * Returns the working day adjuster, which adjusts the date to the n-th following
     * working day (i.e. excluding Saturdays and Sundays).
     * <p>
     * If the argument is 0, the same date is returned if it is a working day otherwise the
     * next working day is returned.
     *
     * @param workingDays the number of working days to add to the date, may be negative
     *
     * @return the working day adjuster, not null
     */
    public static TemporalAdjuster addWorkingDays(long workingDays) {
        return TemporalAdjusters.ofDateAdjuster(d -> addWorkingDays(d, workingDays));
    }

    private static LocalDate addWorkingDays(LocalDate startingDate, long workingDays) {
        if (workingDays == 0) return nextOrSameWorkingDay(startingDate);

        LocalDate result = startingDate;
        int step = Long.signum(workingDays); //are we going forward or backward?

        for (long i = 0; i < Math.abs(workingDays); i++) {
            result = nextWorkingDay(result, step);
        }
        return result;
    }

    private static LocalDate nextOrSameWorkingDay(LocalDate date) {
        return isWeekEnd(date) ? nextWorkingDay(date, 1) : date;
    }

    private static LocalDate nextWorkingDay(LocalDate date, int step) {
        do {
            date = date.plusDays(step);
        } while (isWeekEnd(date));
        return date;
    }

    private static boolean isWeekEnd(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow.equals(DayOfWeek.SATURDAY) || dow.equals(DayOfWeek.SUNDAY);
    }


}
