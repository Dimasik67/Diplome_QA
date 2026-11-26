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
        waitForPicker(DatePicker.class.getName());
        onView(withClassName(equalTo(DatePicker.class.getName())))
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
        waitForPicker(TimePicker.class.getName());
        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(setTime(
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE)
                ));
        clickOk();
    }

    private static void waitForPicker(String className) {
        long endTime = System.currentTimeMillis() + 5000L;
        AssertionError lastError = null;
        do {
            try {
                onView(withClassName(equalTo(className))).check(matches(isDisplayed()));
                return;
            } catch (RuntimeException | AssertionError e) {
                lastError = e instanceof AssertionError ? (AssertionError) e : new AssertionError(e);
                android.os.SystemClock.sleep(100L);
            }
        } while (System.currentTimeMillis() < endTime);
        throw new AssertionError("Picker " + className + " не появился", lastError);
    }

    private static void clickOk() {
        try {
            onView(withId(android.R.id.button1)).perform(click());
        } catch (RuntimeException ignored) {
            onView(withText("OK")).perform(click());
        }
    }
}
