package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
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

public class AddNewsPage {
    public ViewInteraction addNewsTitle;
    public ViewInteraction categoryNews;
    public ViewInteraction itemNews;
    public ViewInteraction publicationDate;
    public ViewInteraction publicationTime;
    public ViewInteraction descriptionNews;
    public ViewInteraction switcherOn;
    public ViewInteraction switcherOff;
    public ViewInteraction saveButton;
    public ViewInteraction cancelButton;
    public ViewInteraction errorDateIcon;
    public ViewInteraction errorItemIcon;
    public ViewInteraction errorTimeIcon;
    public ViewInteraction errorDescriptionIcon;
    public ViewInteraction errorCategoryIcon;
    public ViewInteraction inputCategoryButton;
    public ViewInteraction autoCompleteText;
    public ViewInteraction switcher;
    public ViewInteraction categoryList;
    public ViewInteraction timeErrorMessage;
    public ViewInteraction cancelMessage;
    public ViewInteraction okButton;
    public ViewInteraction cancelButton2;
    public ViewInteraction saveError;




    private final static String TITLE = "Создание";
    private final static String SAVE_BUTTON = "Сохранить";
    private final static String CANCEL_BUTTIN = "Отмена";
    private final static String SWITCHER_ON = "Активна";
    private final static String SWITCHER_OFF = "Не активна";
    private final static String AUTO_COMPLETE = "День рождения";
    private final static String ERROR_ICON = "Ошибка";
    private final static String CATEGORY_BUTTON = "Показать раскрывающееся меню";
    private final static String ERROR_MESSAGE = "Указано недопустимое время";
    private final static String CANCEL_MESSAGE = "Изменения не будут сохранены. Вы действительно хотите выйти?";
    private final static String OK_BUTTON = "OK";
    private final static String CANCEL_BUTTON2 = "ОТМЕНА";
    private final static String SAVE_ERROR = "Сохранение не удалось. Попробуйте позднее.";




    public AddNewsPage() {
        addNewsTitle = onView(allOf(
                withId(R.id.custom_app_bar_title_text_view),
                withText(TITLE)
        ));

        categoryNews = onView(withId(R.id.news_item_category_text_input_layout));
        autoCompleteText = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        itemNews = onView(withId(R.id.news_item_title_text_input_edit_text));
        publicationDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
        publicationTime = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
        descriptionNews = onView(withId(R.id.news_item_description_text_input_edit_text));
        switcher = onView(withId(R.id.switcher));
        switcherOn = onView(allOf(withId(R.id.switcher), withText(SWITCHER_ON)));
        switcherOff = onView(allOf(withId(R.id.switcher), withText(SWITCHER_OFF)));
        saveButton = onView(allOf(withId(R.id.save_button), withContentDescription(SAVE_BUTTON)));
        cancelButton = onView(allOf(withId(R.id.cancel_button), withContentDescription(CANCEL_BUTTIN)));
        errorCategoryIcon = onView(allOf(
                childAtPosition(withId(R.id.news_item_category_text_input_layout), 3),
                withContentDescription(ERROR_ICON)
        ));
        errorDateIcon = onView(allOf(
                childAtPosition(withId(R.id.news_item_create_date_text_input_layout), 3),
                withContentDescription(ERROR_ICON)
        ));
        errorDescriptionIcon = onView(allOf(
                childAtPosition(withId(R.id.news_item_description_text_input_layout), 3),
                withContentDescription(ERROR_ICON)
        ));
        errorItemIcon = onView(allOf(
                childAtPosition(withId(R.id.news_item_title_text_input_layout), 3),
                withContentDescription(ERROR_ICON)
        ));

        errorTimeIcon = onView(allOf(
                childAtPosition(withId(R.id.news_item_publish_time_text_input_layout), 3),
                withContentDescription(ERROR_ICON)
        ));

        inputCategoryButton = onView(allOf(
                childAtPosition(withId(R.id.news_item_category_text_input_layout), 2),
                withContentDescription(CATEGORY_BUTTON)
        ));
        categoryList = onView(CustomViewMatcher.recyclerVievSizeMatcher(8));
        timeErrorMessage = onView(isRoot()).check(matches(withText(ERROR_MESSAGE)));
        cancelMessage = onView(isRoot()).check(matches(withTextOnScreen(CANCEL_MESSAGE)));
        okButton = onView(isRoot()).check(matches(withButtonText(OK_BUTTON)));
        cancelButton = onView(isRoot()).check(matches(withButtonText(CANCEL_BUTTON2)));
        saveError = onView(isRoot()).check(matches(withText(SAVE_ERROR)));


    }

    public void openAddNews() { addNewsTitle.check(matches(isDisplayed())); }
    public void inputCategoryNews(String value) {
        categoryNews.perform(typeText(value));
        closeSoftKeyboardIfNeeded();
    }
    public void getAutoCompleteText() {
        autoCompleteText.perform(typeText(AUTO_COMPLETE));
    }
    public void getCategoryList() {categoryList.check(matches(isDisplayed()));}
    public void inputItemNews(String value) {
        itemNews.perform(typeText(value));
        closeSoftKeyboardIfNeeded();
    }
    private void closeSoftKeyboardIfNeeded() {
        closeSoftKeyboard();
    }
    public void selectPublicationDate(Calendar date) {
        publicationDate.perform(click());
        DateTimePicker.selectDate(date);
    }
    public void selectPublicationTime(Calendar time) {
        publicationTime.perform(click());
        DateTimePicker.selectTime(time);
    }
    public void checkPublicationDateIsEmpty() {
        publicationDate.check(matches(withText("")));
    }
    public void checkPublicationTimeIsEmpty() {
        publicationTime.check(matches(withText("")));
    }
    public void inputNewsDescription(String value) {
        descriptionNews.perform(typeText(value));
        closeSoftKeyboardIfNeeded();
    }
    public void clickSwitcher() {switcher.perform(click());}
    public void checkSwitcherOn() {switcherOn.check(matches(isDisplayed()));}
    public void checkSwitcherOff() {switcherOff.check(matches(isDisplayed()));}
    public void clickSaveButton() {saveButton.perform(click()); }
    public void clickCancelButton() {cancelButton.perform(click()); }
    public void clickCategoryButton() {inputCategoryButton.perform(click()); }
    public void checkErrorCategoryIcon() { errorCategoryIcon.check(matches(isDisplayed())); }
    public void checkErrorItemIcon() {errorItemIcon.check(matches(isDisplayed()));}
    public void checkErrorDateIcon() {errorDateIcon.check(matches(isDisplayed()));}
    public void checkErrorTimeIcon() {errorTimeIcon.check(matches(isDisplayed()));}
    public void checkErrorDescriptionIcon() {errorDescriptionIcon.check(matches(isDisplayed()));}
    public void checkErrorMessage() {timeErrorMessage.check(matches(isDisplayed()));}
    public void checkCancelMessage() {cancelMessage.check(matches(isDisplayed()));}
    public void clickOkButton() {okButton.perform(click());}
    public void clickCancelButton2() {cancelButton2.perform(click()); }
    public void checkSaveErrorMessage() {saveError.check(matches(isDisplayed())); }
    public void inputInvalidTime(String time) {
        publicationTime.perform(replaceText(time));
        closeSoftKeyboardIfNeeded();
    }


}
