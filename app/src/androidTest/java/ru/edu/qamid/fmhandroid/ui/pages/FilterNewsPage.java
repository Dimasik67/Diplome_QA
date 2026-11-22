package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.closeSoftKeyboardIfVisible;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.childAtPosition;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonId;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withContentDescription;

import androidx.test.espresso.ViewInteraction;

import java.util.Calendar;

import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.data.DateTimePicker;
import ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher;

public class FilterNewsPage {
    public ViewInteraction filterNewsTitle;
    public ViewInteraction inputNewsCategory;
    public ViewInteraction startDateFilter;
    public ViewInteraction endDateFilter;
    public ViewInteraction filterButton;
    public ViewInteraction cancelButton;
    public ViewInteraction categorySelectionButton;
    public ViewInteraction errorMessage;
    public ViewInteraction categoryList;
    public ViewInteraction autoComplete;



    private static final String TITLE_TEXT = "Фильтровать новости";
    private static final String CATEGORY_NAME = "Объявление";
    private static final String FILTER_BUTTON_TEXT = "Фильтровать";
    private static final String CANCEL_BUTTON_TEXT = "Отмена";
    private static final String ERROR_MESSAGE = "Некорректный ввод";

    public FilterNewsPage() {
        filterNewsTitle = onView(allOf(
                withId(R.id.filter_news_title_text_view),
                CustomViewMatcher.withContentDescription(TITLE_TEXT)
        ));

        inputNewsCategory = onView(withId(R.id.news_category_text_input_layout));
        categorySelectionButton = onView(allOf(
                childAtPosition(withId(R.id.news_category_text_input_layout), 2),
                withContentDescription("Показать раскрывающееся меню")
        ));
        startDateFilter = onView(withId(R.id.filter_news_date_start_edit_text));
        endDateFilter = onView(withId(R.id.filter_news_date_end_edit_text));
        filterButton = onView(allOf(
                withButtonId(R.id.filter_news_apply_button),
                withButtonText(FILTER_BUTTON_TEXT)
        ));
        cancelButton = onView(allOf(
                withButtonId(R.id.filter_news_cancel_button),
                withButtonText(CANCEL_BUTTON_TEXT)
        ));
        errorMessage = onView(withText(ERROR_MESSAGE));
        categoryList = onView(CustomViewMatcher.recyclerViewSizeMatcher(8));
        autoComplete = onView(withId(R.id.news_category_auto_complete));

    }

    public void getFilterNews() { filterNewsTitle.check(matches(isDisplayed())); }
    public void categoryInput(String value) {
        inputNewsCategory.perform(typeText(value));
        closeSoftKeyboardIfNeeded();
    }
    public void getAutoComplete() {autoComplete.perform(typeText(CATEGORY_NAME));}
    public void inputStartDate(Calendar date) {
        startDateFilter.perform(click());
        DateTimePicker.selectDate(date);
    }
    public void inputEndDate(Calendar date) {
        endDateFilter.perform(click());
        DateTimePicker.selectDate(date);
    }
    public void checkStartDateIsEmpty() {
        startDateFilter.check(matches(withText("")));
    }
    public void checkEndDateIsEmpty() {
        endDateFilter.check(matches(withText("")));
    }
    public void clickCategorySelectionButton() { categorySelectionButton.perform(click()); }
    public void clickFilterButton() { filterButton.perform(click()); }
    public void clickCancelButton() { cancelButton.perform(click()); }
    private void closeSoftKeyboardIfNeeded() {
        closeSoftKeyboardIfVisible();
    }
    public void getCategoryList() {categoryList.check(matches(isDisplayed()));}
    public void checkErrorMessage() { errorMessage.check(matches(isDisplayed())); }
}
