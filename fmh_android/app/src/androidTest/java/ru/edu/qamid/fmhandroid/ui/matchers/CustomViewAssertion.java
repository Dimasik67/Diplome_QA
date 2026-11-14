package ru.edu.qamid.fmhandroid.ui.matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CustomViewAssertion implements ViewAssertion {

    private final Type type;
    private final String expectedText;
    private final boolean isPartialMatch;
    private final SimpleDateFormat dateFormat;
    private final SortOrder sortOrder;

    private enum Type {
        TEXT_MATCH, DATE_SORTING
    }

    public enum SortOrder {
        ASCENDING,  // старые сначала
        DESCENDING  // новые сначала
    }

    private CustomViewAssertion(String text, boolean partial) {
        this.type = Type.TEXT_MATCH;
        this.expectedText = text;
        this.isPartialMatch = partial;
        this.dateFormat = null;
        this.sortOrder = null;
    }

    private CustomViewAssertion(String dateFormatPattern, SortOrder sortOrder) {
        this.type = Type.DATE_SORTING;
        this.expectedText = null;
        this.isPartialMatch = false;
        this.dateFormat = new SimpleDateFormat(dateFormatPattern, Locale.getDefault());
        this.sortOrder = sortOrder;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        switch (type) {
            case TEXT_MATCH:
                boolean found = findTextInViewHierarchy(view, expectedText);
                String message = isPartialMatch
                        ? "Текст, содержащий '" + expectedText + "', не найден"
                        : "Точный текст '" + expectedText + "' не найден";
                assertThat(message, found, is(true));
                break;

            case DATE_SORTING:
                List<Date> dates = extractDatesFromView(view);
                assertThat("Не найдено ни одной даты для анализа", dates.isEmpty(), is(false));

                List<Date> expected = new ArrayList<>(dates);
                if (sortOrder == SortOrder.ASCENDING) {
                    Collections.sort(expected);
                } else {
                    expected.sort(Collections.reverseOrder());
                }

                assertThat("Список не отсортирован " +
                                (sortOrder == SortOrder.ASCENDING ? "по возрастанию" : "по убыванию"),
                        dates, is(expected));
                break;
        }
    }

    private boolean findTextInViewHierarchy(View view, String text) {
        if (view instanceof TextView) {
            CharSequence actual = ((TextView) view).getText();
            if (actual != null) {
                String actualStr = actual.toString();
                if (isPartialMatch) {
                    return actualStr.contains(text);
                } else {
                    return actualStr.equals(text);
                }
            }
        }

        if (view instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                if (findTextInViewHierarchy(group.getChildAt(i), text)) {
                    return true;
                }
            }
        }

        return false;
    }


    private List<Date> extractDatesFromView(View view) {
        List<Date> dates = new ArrayList<>();
        collectDates(view, dates);
        return dates;
    }

    private void collectDates(View view, List<Date> dates) {
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text != null && !text.toString().trim().isEmpty()) {
                try {
                    Date date = dateFormat.parse(text.toString().trim());
                    if (date != null) {
                        dates.add(date);
                    }
                } catch (ParseException ignored) {
                    // Игнорируем не-даты
                }
            }
        }

        if (view instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                collectDates(group.getChildAt(i), dates);
            }
        }
    }


    /**
     * Проверяет наличие точного текста.
     */
    public static CustomViewAssertion hasText(String text) {
        return new CustomViewAssertion(text, false);
    }

    /**
     * Проверяет наличие подстроки.
     */
    public static CustomViewAssertion hasTextContaining(String substring) {
        return new CustomViewAssertion(substring, true);
    }

    /**
     * Проверяет сортировку дат по формату.
     * @param dateFormatPattern Формат даты, например: "dd MMMM yyyy, HH:mm"
     * @param sortOrder Порядок: ASCENDING или DESCENDING
     */
    public static CustomViewAssertion isSortedByDate(String dateFormatPattern, SortOrder sortOrder) {
        return new CustomViewAssertion(dateFormatPattern, sortOrder);
    }
}

