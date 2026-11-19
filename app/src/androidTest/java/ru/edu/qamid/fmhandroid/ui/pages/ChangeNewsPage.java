package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.childAtPosition;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withContentDescription;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withTextOnScreen;

import androidx.test.espresso.ViewInteraction;

import java.util.Calendar;

import ru.edu.qamid.fmhandroid.R;
import ru.edu.qamid.fmhandroid.ui.data.DateTimePicker;
import ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher;

public class ChangeNewsPage {
    public ViewInteraction changeTitle;
    public ViewInteraction category;
    public ViewInteraction itemNews;
    public ViewInteraction selectCategoryButton;
    public ViewInteraction categoryList;
    public ViewInteraction publicationDate;
    public ViewInteraction publicationTime;
    public ViewInteraction descriptionNews;
    public ViewInteraction switcher;
    public ViewInteraction switcherOn;
    public ViewInteraction switcherOff;
    public ViewInteraction saveButton;
    public ViewInteraction cancelButton;
    public ViewInteraction timeErrorMessage;
    public ViewInteraction cancelMessage;
    public ViewInteraction autoComplete;
    public ViewInteraction okButton;
    public ViewInteraction cancelButton2;

    private final static String TITLE = "Редактирование";
    private final static String CATEGORY = "Профсоюз";
    private final static String SAVE_BUTTON = "Сохранить";
    private final static String CANCEL_BUTTON = "Отмена";
    private final static String SWITCHER_ON = "Активна";
    private final static String SWITCHER_OFF = "Не активна";
    private final static String ERROR_MESSAGE = "Указано недопустимое время";
    private final static String CATEGORY_BUTTON = "Показать раскрывающееся меню";
    private final static String CANCEL_MESSAGE = "Изменения не будут сохранены. Вы действительно хотите выйти?";
    private final static String OK_BUTTON = "OK";
    private final static String CANCEL_BUTTON2 = "ОТМЕНА";

    public ChangeNewsPage() {
        changeTitle = onView(allOf(
                withId(R.id.custom_app_bar_title_text_view),
                withText(TITLE)
        ));
        category = onView(withId(R.id.news_item_category_text_input_layout));
        selectCategoryButton = onView(allOf(
                childAtPosition(withId(R.id.news_item_category_text_input_layout), 2),
                withContentDescription(CATEGORY_BUTTON)
        ));
        autoComplete = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        categoryList = onView(CustomViewMatcher.recyclerVievSizeMatcher(8));
        itemNews = onView(withId(R.id.news_item_title_text_input_edit_text));
        publicationDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
        publicationTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
        descriptionNews = onView(withId(R.id.news_item_description_text_input_edit_text));
        switcher = onView(withId(R.id.switcher));
        switcherOn = onView(allOf(withId(R.id.switcher), withText(SWITCHER_ON)));
        switcherOff = onView(allOf(withId(R.id.switcher), withText(SWITCHER_OFF)));
        saveButton = onView(allOf(
                withId(R.id.save_button),
                withContentDescription(SAVE_BUTTON)
        ));
        cancelButton = onView(allOf(
                withId(R.id.cancel_button),
                withContentDescription(CANCEL_BUTTON)
        ));
        cancelMessage = onView(isRoot()).check(matches(withTextOnScreen(CANCEL_MESSAGE)));
        timeErrorMessage = onView(isRoot()).check(matches(withText(ERROR_MESSAGE)));
        okButton = onView(isRoot()).check(matches(withButtonText(OK_BUTTON)));
        cancelButton = onView(isRoot()).check(matches(withButtonText(CANCEL_BUTTON2)));

    }
    public void openChangePage() {changeTitle.check(matches(isDisplayed()));}

    private void closeSoftKeyboardIfNeeded() {
        closeSoftKeyboard();
    }
    public void clickSelectionButton() {selectCategoryButton.perform(click());}
    public void getCategoryList() {categoryList.check(matches(isDisplayed()));}
    public void getAutoComplete() {autoComplete.perform(typeText(CATEGORY)); }
    public void inputItemNews(String value) {
        itemNews.perform(typeText(value));
        closeSoftKeyboardIfNeeded();
    }
    public void selectPublicationDate(Calendar date) {
        publicationDate.perform(click());
        DateTimePicker.selectDate(date);
    }
    public void selectPublicationTime(Calendar time) {
        publicationTime.perform(click());
        DateTimePicker.selectTime(time);
    }

    public void inputPublicationTime(String value) {
        publicationTime.perform(typeText(value));
    }
    public void clickSwitcher() {switcher.perform(click());}
    public void checkSwitcherOn() {switcherOn.check(matches(isDisplayed()));}
    public void checkSwitcherOff() {switcherOff.check(matches(isDisplayed()));}
    public void clickSaveButton() {saveButton.perform(click()); }

    public void clickCancelButton() {cancelButton.perform(click()); }
    public void getCancelMessage() {cancelMessage.check(matches(isDisplayed()));}
    public void getErrorMessage() {timeErrorMessage.check(matches(isDisplayed()));}

    public void clickOkButton() {okButton.perform(click());}
    public void clickCancelButton2() {cancelButton2.perform(click()); }
}
