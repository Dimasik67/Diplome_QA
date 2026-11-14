package ru.edu.qamid.fmhandroid.ui.data;

import java.util.Calendar;

public class TimeHelper {
    public static String getHour(Calendar time) {
        int hour = time.get(Calendar.HOUR_OF_DAY);
        return String.format("%02d", hour); // "13", "09", "00"
    }

    public static String getMinute(Calendar time) {
        int minute = time.get(Calendar.MINUTE);
        return String.format("%02d", minute); // "05", "30"
    }

    public static String getHourMinute(Calendar time) {
        return getHour(time) + ":" + getMinute(time);
    }
}
