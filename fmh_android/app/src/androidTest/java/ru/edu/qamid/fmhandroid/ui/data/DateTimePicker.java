package ru.edu.qamid.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.regex.Pattern.matches;
import static org.hamcrest.Matchers.anyOf;

import android.view.View;

import androidx.test.espresso.Root;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import java.util.Calendar;

import ru.edu.qamid.fmhandroid.ui.matchers.CalendarMatcher;
import ru.edu.qamid.fmhandroid.ui.matchers.TimeMatcher;

public class DateTimePicker {
    public static void selectDateTime(Calendar dateTime) {
        selectDate(dateTime);
        selectTime(dateTime);
    }

    /**
     * Только дата
     */
    public static void selectDate(Calendar date) {
        String monthYear = CalendarHelper.getMonthYear(date);
        String day = CalendarHelper.getDayString(date);

        while (true) {
            try {
                onView(withText(monthYear)).check(matches(isDisplayed()));
                break;
            } catch (Exception e) {
                onView(anyOf(withContentDescription("Next month"),
                        withText("›")
                )).perform(click());
            }
        }

        onView(withText(day))
                .inRoot(CalendarMatcher.hasDate(date))
                .perform(click());
    }

    /**
     * Только время
     */
    public static void selectTime(Calendar time) {
        String hour = TimeHelper.getHour(time);
        String minute = TimeHelper.getMinute(time);

        onView(withText(hour))
                .inRoot(TimeMatcher.hasTime(time))
                .perform(click());

        onView(withText(minute))
                .inRoot(TimeMatcher.hasTime(time))
                .perform(click());

        // Подтверждение (если есть)
        try {
            onView(withText("OK")).inRoot(isDialog()).perform(click());
        } catch (Exception ignored) {}
    }

    // Вспомогательные методы (можно вынести в BaseMatchers)
    private static Matcher<View> withContentDescription(String desc) {
        return ViewMatchers.withContentDescription(desc);
    }

    private static Matcher<Root> isDialog() {
        return RootMatchers.isDialog();
    }
}
