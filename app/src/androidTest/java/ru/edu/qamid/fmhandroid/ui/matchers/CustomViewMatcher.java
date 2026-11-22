package ru.edu.qamid.fmhandroid.ui.matchers;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomViewMatcher {

    /**
     * Матчер кнопки по её тексту.
     */
    public static Matcher<View> withButtonText(final String expectedText) {
        return new BoundedMatcher<View, Button>(Button.class) {
            @Override
            protected boolean matchesSafely(Button button) {
                return button.getText() != null && button.getText().toString().equals(expectedText);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with button text: ").appendValue(expectedText);
            }
        };
    }

    /**
     * Матчер кнопки по её идентификатору.
     */
    public static Matcher<View> withButtonId(@IdRes final int resourceId) {
        return new BoundedMatcher<View, Button>(Button.class) {
            @Override
            protected boolean matchesSafely(Button button) {
                return button.getId() == resourceId;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with button id: ").appendValue(resourceId);
            }
        };
    }

    /**
     * Универсальный матчер по идентификатору для любых видов.
     */
    public static Matcher<View> withViewId(@IdRes final int resourceId) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            protected boolean matchesSafely(View item) {
                return item.getId() == resourceId;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with view id: ").appendValue(resourceId);
            }
        };
    }

    /**
     * Матчер по contentDescription.
     */
    public static Matcher<View> withContentDescription(final String expectedContentDescription) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            protected boolean matchesSafely(View item) {
                CharSequence contentDesc = item.getContentDescription();
                return contentDesc != null && contentDesc.toString().equals(expectedContentDescription);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with content description: ").appendValue(expectedContentDescription);
            }
        };
    }

    /**
     * Матчер по позиции дочернего элемента внутри родителя.
     */
    public static Matcher<View> childAtPosition(Matcher<View> parentMatcher, final int position) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position ")
                        .appendValue(position)
                        .appendText(" in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(View item) {
                ViewParent viewParent = item.getParent();
                if (viewParent == null || !(viewParent instanceof ViewGroup)) {
                    return false;
                }

                ViewGroup groupParent = (ViewGroup) viewParent;
                return parentMatcher.matches(groupParent) && groupParent.indexOfChild(item) == position;
            }
        };
    }

    public static BoundedMatcher<View, RecyclerView> recyclerViewSizeMatcher(final int matcherSize) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("Requested list size: " + matcherSize);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter == null) {
                    return matcherSize == 0;
                }
                int actualSize = adapter.getItemCount();
                return actualSize == matcherSize;
            }
        };
    }

    /**
     * Матчер, который проверяет наличие текста в любом TextView на экране,
     * независимо от глубины вложенности.
     */
    public static Matcher<View> withTextOnScreen(final String expectedText) {
        return new BoundedMatcher<View, View>(View.class) {
            private boolean found = false;

            @Override
            protected boolean matchesSafely(View view) {
                found = false;
                searchInHierarchy(view, expectedText);
                return found;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text on screen: ").appendValue(expectedText);
            }

            // Рекурсивный обход всей иерархии
            private void searchInHierarchy(View root, String text) {
                if (root instanceof TextView) {
                    CharSequence actual = ((TextView) root).getText();
                    if (actual != null && actual.toString().equals(text)) {
                        found = true;
                    }
                }

                if (root instanceof ViewGroup) {
                    ViewGroup group = (ViewGroup) root;
                    for (int i = 0; i < group.getChildCount(); i++) {
                        if (found) return; // Оптимизация: выходим при нахождении
                        searchInHierarchy(group.getChildAt(i), text);
                    }
                }
            }
        };
    }
    /**
     * Матчер, который ищет кнопку (Button или подобный) с указанным текстом
     * во всей иерархии представлений — включая выпадающие меню, диалоги, Bottom Sheets.
     */
    public static Matcher<View> withButtonOnScreen(final String expectedText) {
        return new BoundedMatcher<View, View>(View.class) {
            private boolean found = false;

            @Override
            protected boolean matchesSafely(View view) {
                found = false;
                searchInHierarchy(view, expectedText);
                return found;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with button containing text: ").appendValue(expectedText);
            }

            private void searchInHierarchy(View root, String text) {
                if (found) return; // Оптимизация

                // Проверяем, является ли View кнопкой по типу или поведению
                if (root instanceof Button || (root instanceof TextView && root.isClickable())) {
                    CharSequence buttonText = ((TextView) root).getText();
                    if (buttonText != null && buttonText.toString().equals(text)) {
                        found = true;
                        return;
                    }
                }

                // Рекурсивно ищем в дочерних элементах
                if (root instanceof ViewGroup) {
                    ViewGroup group = (ViewGroup) root;
                    for (int i = 0; i < group.getChildCount(); i++) {
                        searchInHierarchy(group.getChildAt(i), text);
                        if (found) return;
                    }
                }
            }
        };
    }



}