package ru.edu.qamid.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingRootException;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher;

public class DataHelper {


    public static ViewAction waitDisplayed(final int viewId, final long timeoutMs) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for element with ID <" + viewId + "> to be displayed within " + timeoutMs + "ms.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                final long startTime = System.currentTimeMillis();
                final long maxEndTime = startTime + timeoutMs;

                do {
                    uiController.loopMainThreadUntilIdle();

                    View rootView = view.getRootView();
                    View targetView = rootView.findViewById(viewId);

                    if (targetView != null && targetView.isShown()) {
                        return;
                    }


                    uiController.loopMainThreadForAtLeast(100L);

                } while (System.currentTimeMillis() < maxEndTime);

                throw new IllegalStateException("Element with ID <" + viewId + "> did not become visible within " + timeoutMs + "ms");
            }
        };
    }
    /**
     * Ожидает появления любого из перечисленных view. Полезно на splash-экране,
     * когда приложение может уйти как на форму авторизации, так и на главную страницу.
     */
    public static ViewAction waitDisplayedAny(final long timeoutMs, final int... viewIds) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for any of elements to be displayed within " + timeoutMs + "ms.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                final long maxEndTime = System.currentTimeMillis() + timeoutMs;
                do {
                    uiController.loopMainThreadUntilIdle();
                    View rootView = view.getRootView();
                    for (int viewId : viewIds) {
                        View targetView = rootView.findViewById(viewId);
                        if (targetView != null && targetView.isShown()) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(100L);
                } while (System.currentTimeMillis() < maxEndTime);

                throw new IllegalStateException("Ни один из элементов не появился за " + timeoutMs + " мс");
            }
        };
    }

    public static void closeSoftKeyboardIfVisible() {
        onView(isRoot()).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "close soft keyboard if it is visible";
            }

            @Override
            public void perform(UiController uiController, View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null && inputMethodManager.isAcceptingText()) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                uiController.loopMainThreadUntilIdle();
            }
        });
    }

    public static boolean isViewDisplayed(int viewId) {
        try {
            onView(withId(viewId)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException | NoMatchingRootException | AssertionError ignored) {
            return false;
        }
    }

    /**
     * Проверка отображения с ожиданием. Переходы между экранами и появление
     * popup-меню не всегда попадают в Espresso IdlingResource, поэтому обычная
     * проверка isDisplayed() может сработать до того, как view будет показан.
     */
    public static void checkDisplayedWithRetry(ViewInteraction view) {
        checkDisplayedWithRetry(view, 5000L);
    }

    public static void checkDisplayedWithRetry(ViewInteraction view, long timeoutMs) {
        final long endTime = System.currentTimeMillis() + timeoutMs;
        AssertionError lastError = null;
        do {
            try {
                view.check(matches(isDisplayed()));
                return;
            } catch (NoMatchingViewException | AssertionError error) {
                lastError = error instanceof AssertionError
                        ? (AssertionError) error
                        : new AssertionError(error);
                SystemClock.sleep(100L);
            }
        } while (System.currentTimeMillis() < endTime);

        throw new AssertionError("View не отображался в течение " + timeoutMs + " мс", lastError);
    }

    public static ViewAction clickView() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isEnabled());
            }

            @Override
            public String getDescription() {
                return "perform click on view";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.performClick();
                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    public static int getItemCount(int recyclerViewId) {
        final int[] count = {0};
        onView(withId(recyclerViewId)).check((view, e) -> {
            if (e != null) throw e;
            RecyclerView rv = (RecyclerView) view;
            RecyclerView.Adapter adapter = rv.getAdapter();
            count[0] = (adapter != null) ? adapter.getItemCount() : 0;
        });
        return count[0];
    }

    public static void checkListSizeChangedBy(int recyclerViewId, int delta) {
        int initialCount = getItemCount(recyclerViewId);
        int expectedCount = initialCount + delta;

        // Espresso автоматически ждёт, пока RecyclerView не обновится
        onView(withId(recyclerViewId))
                .check(matches(CustomViewMatcher.recyclerViewSizeMatcher(expectedCount)));
    }

    public static void waitForListSize(int recyclerViewId, int expectedCount) {
        onView(withId(recyclerViewId))
                .check(matches(CustomViewMatcher.recyclerViewSizeMatcher(expectedCount)));
    }

}