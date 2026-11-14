package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
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

import ru.edu.qamid.fmhandroid.R;
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
    public ViewInteraction errorMassage;
    public ViewInteraction categoryList;
    public ViewInteraction autoComplete;



    private static final String TITLE_TEXT = "Фильтровать новости";
    private static final String CATEGORY_NAME = "Объявление";
    private static final String FILTER_BUTTON_TEXT = "Фильтровать";
    private static final String CANCEL_BUTTON_TEXT = "Отмена";
    private static final String ERROR_MASSAGE = "Некорректный ввод";

    public FilterNewsPage() {
        filterNewsTitle = onView(allOf(
                withId(R.id.filter_news_title_text_view),
                CustomViewMatcher.withContentDescription(TITLE_TEXT)
        ));

        inputNewsCategory = onView(withId(R.id.news_item_category_text_input_layout));
        categorySelectionButton = onView(allOf(
                childAtPosition(withId(R.id.news_item_category_text_input_layout), 2),
                withContentDescription("Показать раскрывающееся меню")
        ));
        startDateFilter = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
        endDateFilter = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
        filterButton = onView(allOf(
                withButtonId(R.id.filter_button),
                withButtonText(FILTER_BUTTON_TEXT)
        ));
        cancelButton = onView(allOf(
                withButtonId(R.id.cancel_button),
                withButtonText(CANCEL_BUTTON_TEXT)
        ));
        errorMassage = onView(withText(ERROR_MASSAGE));
        categoryList = onView(CustomViewMatcher.recyclerVievSizeMatcher(8));
        autoComplete = onView(withId(R.id.news_item_category_text_auto_complete_text_view));

    }

    public void getFilterNews() { filterNewsTitle.check(matches(isDisplayed())); }
    public void categoryInput(String value) {
        inputNewsCategory.perform(typeText(value));
        closeSoftKeyboardIfNeeded();
    }
    public void getAutoComplete() {autoComplete.perform(typeText(CATEGORY_NAME));}
    public void inputStartData(Calendar date) {
        startDateFilter.perform(click());
        DateTimePicker.selectDate(date);
    }
    public void inputEndData(Calendar date) {
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
        closeSoftKeyboard();
    }
    public void getCategoryList() {categoryList.check(matches(isDisplayed()));}
    public void checkErrorMassage() { errorMassage.check(matches(isDisplayed())); }
}
