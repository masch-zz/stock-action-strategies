package org.masch.exercise.stock.util;

import java.util.Date;
import java.util.Calendar;

public class DateUtils {

    public static boolean isLastDayOfTheMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
    }

}
