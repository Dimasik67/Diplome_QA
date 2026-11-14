package ru.edu.qamid.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
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
                return null;
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
                .check(matches(CustomViewMatcher.recyclerVievSizeMatcher(expectedCount)));
    }

    public static void waitForListSize(int recyclerViewId, int expectedCount) {
        onView(withId(recyclerViewId))
                .check(matches(CustomViewMatcher.recyclerVievSizeMatcher(expectedCount)));
    }

}