package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.closeSoftKeyboardIfVisible;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withContentDescription;

import androidx.test.espresso.ViewInteraction;

import java.util.Calendar;

import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.data.DateTimePicker;
import ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher;

public class FilterOnEditNewsPage {
    public ViewInteraction filterTitle;
    public ViewInteraction category;
    public ViewInteraction startDate;
    public ViewInteraction endDate;
    public ViewInteraction checkBoxActive;
    public ViewInteraction checkBoxNotActive;
    public ViewInteraction filterButton;
    public ViewInteraction cancelButton;
    public ViewInteraction categoryList;
    public ViewInteraction selectCategoryButton;
    public ViewInteraction autoCompleteCategory;


    private final static String TITLE = "Фильтровать новости";
    private final static String FILTER_BUTTON = "Фильтровать";
    private final static String CANCEL_BUTTON = "Отмена";
    private final static String ACTIVE = "Активна";
    private final static String NOT_ACTIVE = "Не активна";
    private final static String AUTO_COMPLETE = "Массаж";
    private final static String SELECT_BUTTON = "Показать раскрывающееся меню";

    public FilterOnEditNewsPage() {
        filterTitle = onView(allOf(
                withId(R.id.filter_news_title_text_view),
                withText(TITLE)
        ));
        category = onView(withId(R.id.filter_news_category_auto_complete));
        categoryList = onView(withText(AUTO_COMPLETE)).inRoot(isPlatformPopup());
        selectCategoryButton = onView(allOf(
                isDisplayed(),
                withContentDescription(SELECT_BUTTON)));
        startDate = onView(withId(R.id.filter_news_date_start_edit_text));
        endDate = onView(withId(R.id.filter_news_date_end_edit_text));
        checkBoxActive = onView(allOf(
                withId(R.id.filter_news_active_check_box),
                withText(ACTIVE)
        ));
        checkBoxNotActive = onView(allOf(
                withId(R.id.filter_news_inactive_check_box),
                withText(NOT_ACTIVE)
        ));
        autoCompleteCategory = onView(withId(R.id.filter_news_category_auto_complete));
        filterButton = onView(allOf(
                withId(R.id.filter_news_apply_button),
                withText(FILTER_BUTTON)
        ));
        cancelButton = onView(allOf(
                withId(R.id.filter_news_cancel_button),
                withText(CANCEL_BUTTON)
        ));
    }
    public void getFilter() {filterTitle.check(matches(isDisplayed()));}
    public void inputCategory(String value) {
        category.perform(replaceText(value));
        closeSoftKeyboardIfNeeded();
    }
    public void clickSelectButton() {selectCategoryButton.perform(click());}
    public void getCategoryList() {categoryList.check(matches(isDisplayed()));}
    public void getAutoCompleteCategory() {autoCompleteCategory.perform(replaceText(AUTO_COMPLETE));}
    private void closeSoftKeyboardIfNeeded() {
        closeSoftKeyboardIfVisible();
    }
    public void selectStartDate(Calendar date) {
        startDate.perform(click());
        DateTimePicker.selectDate(date);
    }
    public void selectEndDate(Calendar date) {
        endDate.perform(click());
        DateTimePicker.selectDate(date);
    }
    public void checkStartDateIsEmpty() {
        startDate.check(matches(withText("")));
    }
    public void checkEndDateIsEmpty() {
        endDate.check(matches(withText("")));
    }
    public void clickCheckBoxActive() {checkBoxActive.perform(click());}
    public void clickCheckBoxNotActive() {checkBoxNotActive.perform(click());}
    public void clickFilterButton() {filterButton.perform(click());}
    public void clickCancelButton() {cancelButton.perform(click()); }


}
