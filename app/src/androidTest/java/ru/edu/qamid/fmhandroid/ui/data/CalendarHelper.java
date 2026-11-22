package ru.edu.qamid.fmhandroid.ui.data;

import java.util.Calendar;

public class CalendarHelper {
    private static final String[] MONTHS_RU = {
            "январь", "февраль", "март", "апрель", "май", "июнь",
            "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"
    };

    // Родительный падеж используется в contentDescription ячеек DatePicker
    private static final String[] MONTHS_RU_GENITIVE = {
            "января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря"
    };

    // Текущая дата
    public static String getCurrentMonthYear() {
        return getMonthYear(Calendar.getInstance());
    }

    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    // Для произвольной даты
    public static String getMonthYear(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return (MONTHS_RU[month] + " " + year).toLowerCase();
    }

    public static int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public static String getDayString(Calendar date) {
        return String.valueOf(date.get(Calendar.DAY_OF_MONTH));
    }

    public static String getDayContentDescription(Calendar date) {
        return getDayString(date) + " " + MONTHS_RU_GENITIVE[date.get(Calendar.MONTH)]
                + " " + date.get(Calendar.YEAR);
    }
}
