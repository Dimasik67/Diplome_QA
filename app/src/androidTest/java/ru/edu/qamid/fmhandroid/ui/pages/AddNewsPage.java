package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.checkDisplayedWithRetry;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.closeSoftKeyboardIfVisible;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withContentDescription;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withTextOnScreen;

import android.os.SystemClock;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ListAdapter;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Matcher;

import java.util.Calendar;

import ru.edu.qamid.R;
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
    private final static String CANCEL_BUTTON = "Отмена";
    private final static String SWITCHER_ON = "Активна";
    private final static String SWITCHER_OFF = "Не активна";
    private final static String AUTO_COMPLETE = "День рождения";
    private final static String CATEGORY_BUTTON = "Показать раскрывающееся меню";
    private final static String ERROR_MESSAGE = "Указано недопустимое время";
    private final static int CATEGORIES_COUNT = 8;
    private final static String CANCEL_MESSAGE = "Изменения не будут сохранены. Вы действительно хотите выйти?";
    private final static String OK_BUTTON = "OK";
    private final static String CANCEL_BUTTON2 = "ОТМЕНА";
    private final static String SAVE_ERROR = "Сохранение не удалось. Попробуйте позднее.";




    public AddNewsPage() {
        addNewsTitle = onView(allOf(
                withId(R.id.custom_app_bar_title_text_view),
                withText(TITLE)
        ));

        categoryNews = onView(withId(R.id.news_category_auto_complete));
        autoCompleteText = onView(withId(R.id.news_category_auto_complete));
        itemNews = onView(withId(R.id.news_title_edit_text));
        publicationDate = onView(withId(R.id.news_publish_date_edit_text));
        publicationTime = onView(withId(R.id.news_publish_time_edit_text));
        descriptionNews = onView(withId(R.id.news_description_edit_text));
        switcher = onView(withId(R.id.news_active_switch));
        switcherOn = onView(allOf(withId(R.id.news_active_switch), withText(SWITCHER_ON)));
        switcherOff = onView(allOf(withId(R.id.news_active_switch), withText(SWITCHER_OFF)));
        saveButton = onView(allOf(withId(R.id.news_save_button), withContentDescription(SAVE_BUTTON)));
        cancelButton = onView(allOf(withId(R.id.news_cancel_button), withContentDescription(CANCEL_BUTTON)));
        inputCategoryButton = onView(withId(R.id.news_category_auto_complete));
        categoryList = onView(CustomViewMatcher.recyclerViewSizeMatcher(8));
        timeErrorMessage = onView(withText(ERROR_MESSAGE));
        cancelMessage = onView(isRoot());
        okButton = onView(withButtonText(OK_BUTTON));
        cancelButton2 = onView(withButtonText(CANCEL_BUTTON2));
        saveError = onView(withText(SAVE_ERROR));


    }

    public void openAddNews() { checkDisplayedWithRetry(addNewsTitle); }
    public void inputCategoryNews(String value) {
        categoryNews.perform(replaceText(value));
        closeSoftKeyboardIfNeeded();
    }
    public void getAutoCompleteText() {
        autoCompleteText.perform(replaceText(AUTO_COMPLETE));
    }
    public void getCategoryList() {
        for (int attempt = 0; attempt < 30; attempt++) {
            try {
                categoryNews.check((view, error) -> {
                    if (error != null) throw error;
                    AutoCompleteTextView autoComplete = (AutoCompleteTextView) view;
                    ListAdapter adapter = autoComplete.getAdapter();
                    int actualCount = adapter == null ? 0 : adapter.getCount();
                    if (actualCount != CATEGORIES_COUNT) {
                        throw new AssertionError("Ожидалось категорий: " + CATEGORIES_COUNT
                                + ", фактически: " + actualCount);
                    }
                });
                return;
            } catch (AssertionError | NoMatchingViewException ignored) {
                SystemClock.sleep(100L);
            }
        }
        throw new AssertionError("Список категорий не загрузился за 3 с");
    }
    public void inputItemNews(String value) {
        itemNews.perform(replaceText(value));
        closeSoftKeyboardIfNeeded();
    }
    private void closeSoftKeyboardIfNeeded() {
        closeSoftKeyboardIfVisible();
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
        descriptionNews.perform(replaceText(value));
        closeSoftKeyboardIfNeeded();
    }
    public void clickSwitcher() {switcher.perform(click());}
    public void setSwitcherState(boolean active) {
        switcher.perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(CompoundButton.class);
            }

            @Override
            public String getDescription() {
                return "установить статус новости";
            }

            @Override
            public void perform(UiController uiController, View view) {
                CompoundButton button = (CompoundButton) view;
                if (button.isChecked() != active) {
                    button.performClick();
                }
            }
        });
    }
    public void checkSwitcherOn() {switcherOn.check(matches(isDisplayed()));}
    public void checkSwitcherOff() {switcherOff.check(matches(isDisplayed()));}
    public void clickSaveButton() {saveButton.perform(scrollTo(), click()); }
    public void clickCancelButton() {cancelButton.perform(click()); }
    public void clickCategoryButton() {inputCategoryButton.perform(click()); }
    public void checkErrorCategoryIcon() {
        onView(withId(R.id.news_category_text_input_layout)).check((view, error) -> {
            if (error != null) throw error;
            TextInputLayout layout = (TextInputLayout) view;
            if (!layout.isStartIconVisible()) {
                throw new AssertionError("Ожидалась иконка ошибки у категории");
            }
        });
    }

    public void checkErrorItemIcon() { checkEndIconVisible(R.id.news_title_text_input_layout); }
    public void checkErrorDateIcon() { checkEndIconVisible(R.id.news_publish_date_text_input_layout); }
    public void checkErrorTimeIcon() { checkEndIconVisible(R.id.news_publish_time_text_input_layout); }
    public void checkErrorDescriptionIcon() { checkEndIconVisible(R.id.news_description_text_input_layout); }

    private void checkEndIconVisible(int layoutId) {
        onView(withId(layoutId)).check((view, error) -> {
            if (error != null) throw error;
            TextInputLayout layout = (TextInputLayout) view;
            if (layout.getEndIconMode() != TextInputLayout.END_ICON_CUSTOM) {
                throw new AssertionError("Ожидалась иконка ошибки в поле");
            }
        });
    }
    public void checkErrorMessage() {timeErrorMessage.check(matches(isDisplayed()));}
    public void checkCancelMessage() {cancelMessage.check(matches(withTextOnScreen(CANCEL_MESSAGE)));}
    public void clickOkButton() {okButton.perform(click());}
    public void clickCancelButton2() {cancelButton2.perform(click()); }
    public void checkSaveErrorMessage() {saveError.check(matches(isDisplayed())); }
    public void inputInvalidTime(String time) {
        publicationTime.perform(replaceText(time));
        closeSoftKeyboardIfNeeded();
    }
    public void checkTimeFieldValue(String time) {
        publicationTime.check(matches(withText(time)));
    }


}
