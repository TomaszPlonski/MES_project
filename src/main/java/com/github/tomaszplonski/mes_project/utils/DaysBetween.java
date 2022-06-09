package com.github.tomaszplonski.mes_project.utils;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DaysBetween {

    /**
     * Returns the days between two dates, taking into account working day.
     *
     * @param date1Include Temporal -> first date, included
     * @param date2Exclude Temporal ->second date, excluded
     *
     * @return int -> working days between two dates
     */

    public static int daysBetween(Temporal date1Include, Temporal date2Exclude){
        int days = Math.toIntExact(ChronoUnit.DAYS.between(date1Include, date2Exclude));
        if (date1Include.with(WorkingDays.addWorkingDays(days)).equals(date2Exclude)){
            return days;
        }
        if (days>0) {
            return days - 2;
        } else {
            return days + 2;
        }

    }
}
