package ru.edu.qamid.fmhandroid.ui.matchers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.espresso.Root;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.edu.qamid.fmhandroid.ui.data.CalendarHelper;

public class CalendarMatcher {
    /**
     * Возвращает матчер, который ищет календарь с указанной датой.
     * Если дата не передана — использует текущую.
     */
    public static Matcher<Root> isCalendarWithDate(Calendar date) {
        if (date == null) {
            date = Calendar.getInstance();
        }

        final String expectedMonthYear = CalendarHelper.getMonthYear(date);
        final int expectedDay = CalendarHelper.getDay(date);

        return new BoundedMatcher<>(Root.class) {
            private String mismatchReason = "неизвестно";

            @Override
            public void describeTo(Description description) {
                description.appendText("ожидался календарь с датой: " +
                        expectedDay + " " + expectedMonthYear);
            }

            @Override
            protected boolean matchesSafely(Root root) {
                try {
                    View decorView = root.getDecorView();
                    if (decorView == null) {
                        mismatchReason = "decorView == null";
                        return false;
                    }

                    List<TextView> textViews = getAllTextViews(decorView);
                    if (textViews.isEmpty()) {
                        mismatchReason = "нет TextView";
                        return false;
                    }

                    boolean hasExpectedMonthYear = false;
                    boolean hasExpectedDay = false;

                    List<String> foundTexts = new ArrayList<>();

                    for (TextView tv : textViews) {
                        String text = tv.getText().toString().trim().toLowerCase();
                        if (text.isEmpty()) continue;
                        foundTexts.add(text);

                        // Проверяем месяц и год: "март 2025"
                        if (text.equals(expectedMonthYear)) {
                            hasExpectedMonthYear = true;
                        }

                        // Проверяем число: "15"
                        if (isMatchingDay(text, expectedDay)) {
                            hasExpectedDay = true;
                        }
                    }

                    boolean result = hasExpectedMonthYear && hasExpectedDay;
                    if (!result) {
                        mismatchReason = String.format(
                                "не хватает: месяц/год=%s, число=%s | найдено: %s",
                                hasExpectedMonthYear, hasExpectedDay, String.join(", ", foundTexts)
                        );
                    }

                    return result;
                } catch (Exception e) {
                    mismatchReason = "ошибка: " + e.getMessage();
                    return false;
                }
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText(mismatchReason);
            }

            // Проверяет, совпадает ли текст с ожидаемым числом
            private boolean isMatchingDay(String text, int day) {
                try {
                    int value = Integer.parseInt(text);
                    return value == day;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        };
    }

    // Упрощённый метод: проверка календаря с текущей датой
    public static Matcher<Root> isCalendarWithCurrentDate() {
        return isCalendarWithDate(Calendar.getInstance());
    }

    // Рекурсивный сбор всех TextView
    private static List<TextView> getAllTextViews(View view) {
        List<TextView> result = new ArrayList<>();
        if (view instanceof TextView) {
            result.add((TextView) view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                result.addAll(getAllTextViews(group.getChildAt(i)));
            }
        }
        return result;
    }

    public static Matcher<Root> hasDate(Calendar targetDate) {
        final String expectedMonthYear = CalendarHelper.getMonthYear(targetDate);
        final String expectedDay = CalendarHelper.getDayString(targetDate);

        return new BoundedMatcher<>(Root.class) {
            private String mismatchReason = "неизвестно";

            @Override
            public void describeTo(Description description) {
                description.appendText("ожидается календарь с: " + expectedMonthYear + ", число " + expectedDay);
                if (!"неизвестно".equals(mismatchReason)) {
                    description.appendText(" | " + mismatchReason);
                }
            }

            @Override
            protected boolean matchesSafely(Root root) {
                try {
                    View decorView = root.getDecorView();
                    if (decorView == null) return false;

                    List<TextView> textViews = getAllTextViews(decorView);
                    boolean hasMonthYear = false, hasDay = false;
                    List<String> foundTexts = new ArrayList<>();

                    for (TextView tv : textViews) {
                        String text = tv.getText().toString().trim().toLowerCase();
                        if (text.isEmpty()) continue;
                        foundTexts.add(text);
                        if (text.equals(expectedMonthYear)) hasMonthYear = true;
                        if (text.equals(expectedDay)) hasDay = true;
                    }

                    mismatchReason = "месяц/год=" + hasMonthYear + ", число=" + hasDay
                            + " | найдено: " + String.join(", ", foundTexts);
                    return hasMonthYear && hasDay;
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText(mismatchReason);
            }

            private List<TextView> getAllTextViews(View view) {
                List<TextView> result = new ArrayList<>();
                if (view instanceof TextView) result.add((TextView) view);
                if (view instanceof ViewGroup) {
                    ViewGroup group = (ViewGroup) view;
                    for (int i = 0; i < group.getChildCount(); i++) {
                        result.addAll(getAllTextViews(group.getChildAt(i)));
                    }
                }
                return result;
            }
        };
    }
}
