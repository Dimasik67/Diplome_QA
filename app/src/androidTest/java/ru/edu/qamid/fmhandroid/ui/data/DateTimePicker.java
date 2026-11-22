package ru.edu.qamid.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;

import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateTimePicker {
    public static void selectDateTime(Calendar dateTime) {
        selectDate(dateTime);
        selectTime(dateTime);
    }

    /**
     * Только дата
     */
    public static void selectDate(Calendar date) {
        onView(withClassName(equalTo(DatePicker.class.getName())))
                .check(matches(isDisplayed()))
                .perform(setDate(
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH) + 1,
                        date.get(Calendar.DAY_OF_MONTH)
                ));
        clickOk();
    }

    /**
     * Только время
     */
    public static void selectTime(Calendar time) {
        onView(withClassName(equalTo(TimePicker.class.getName())))
                .check(matches(isDisplayed()))
                .perform(setTime(
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE)
                ));
        clickOk();
    }

    private static void clickOk() {
        try {
            onView(withId(android.R.id.button1)).perform(click());
        } catch (RuntimeException ignored) {
            onView(withText("OK")).perform(click());
        }
    }
}
