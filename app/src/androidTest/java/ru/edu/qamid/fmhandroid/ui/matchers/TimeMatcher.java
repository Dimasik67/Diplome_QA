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

import ru.edu.qamid.fmhandroid.ui.data.TimeHelper;

public class TimeMatcher {
    public static Matcher<Root> hasTime(Calendar targetTime) {
        final String expectedHour = TimeHelper.getHour(targetTime);
        final String expectedMinute = TimeHelper.getMinute(targetTime);

        return new BoundedMatcher<>(Root.class) {
            private String mismatchReason = "неизвестно";

            @Override
            public void describeTo(Description description) {
                description.appendText("ожидается пикер времени с: " + expectedHour + ":" + expectedMinute);
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
                    boolean hasHour = false, hasMinute = false;

                    for (TextView tv : textViews) {
                        String text = tv.getText().toString().trim();
                        if (text.equals(expectedHour)) hasHour = true;
                        if (text.equals(expectedMinute)) hasMinute = true;
                    }

                    boolean result = hasHour && hasMinute;
                    if (!result) {
                        mismatchReason = String.format("не найдено: час=%s, минуты=%s", hasHour, hasMinute);
                    }

                    return result;
                } catch (Exception e) {
                    mismatchReason = "ошибка: " + e.getMessage();
                    return false;
                }
            }

            @Override
            public void describeMismatchSafely(Root root, Description description) {
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
