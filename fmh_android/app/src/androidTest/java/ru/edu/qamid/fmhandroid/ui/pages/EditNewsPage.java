package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.getItemCount;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewAssertion.hasText;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewAssertion.isSortedByDate;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.childAtPosition;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.recyclerVievSizeMatcher;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withContentDescription;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withTextOnScreen;

import androidx.test.espresso.ViewInteraction;

import ru.edu.qamid.fmhandroid.R;
import ru.edu.qamid.fmhandroid.ui.matchers.CustomViewAssertion;

public class EditNewsPage {
    public ViewInteraction controlPanel;
    public ViewInteraction addNewsButton;
    public ViewInteraction sortNewsButton;
    public ViewInteraction filterNewsButton;
    public ViewInteraction newsList;
    public ViewInteraction newsCard;
    public ViewInteraction newsItem;
    public ViewInteraction newsCreationDate;
    public ViewInteraction newsPublicationDate;
    public ViewInteraction newsStatusActive;
    public ViewInteraction newsStatusNotActive;
    public ViewInteraction deleteNewsButton;
    public ViewInteraction editNewsButton;
    public ViewInteraction newsDescriptionButton;
    public ViewInteraction newsDescriptionText;
    public ViewInteraction deleteMessage;
    public ViewInteraction okButton;
    public ViewInteraction cancelButton;
    public ViewInteraction categoryIcon;


    private final static String CONTROL_PANEL_TEXT = "Панель управления";
    private final static String ADD_NEWS_BUTTON = "Кнопка добавлениея новости";
    private final static String ACTIVE_NEWS = "Активна";
    private final static String NOT_ACTIVE_NEWS = "Не активна";
    private final static String DELETE_MESSAGE = "Вы уверены, что хотите безвозвратно удалить документ? Данные изменения нельзя будет отменить в будущем.";
    private final static String OK_BUTTON = "OK";
    private final static String CANCEL_BUTTON = "ОТМЕНА";
    private final static String CATEGORY_ICON = "Иконка категории нововсти";
    private final static String ICON_TEXT = "Массаж";
    private int initialNewsCount;

    public EditNewsPage() {
        controlPanel = onView(withText(CONTROL_PANEL_TEXT));
        addNewsButton = onView(allOf(
                withId(R.id.add_news_image_view),
                withContentDescription(ADD_NEWS_BUTTON)
        ));
        sortNewsButton = onView(withId(R.id.sort_news_material_button));
        filterNewsButton = onView(withId(R.id.filter_news_material_button));
        newsCard = onView(allOf(
                childAtPosition(withId(R.id.news_list_recycler_view), 2),
                        withId(R.id.news_item_material_card_view)));
        newsItem = onView(withId(R.id.news_item_title_text_view));
        newsCreationDate = onView(withId(R.id.news_item_create_date_text_view));
        newsPublicationDate = onView(withId(R.id.news_item_publication_date_text_view));
        newsStatusActive = onView(allOf(
                withId(R.id.news_item_published_text_view),
                withText(ACTIVE_NEWS)
        ));
        newsStatusNotActive = onView(allOf(
                withId(R.id.news_item_published_text_view),
                withText(NOT_ACTIVE_NEWS)
        ));
        deleteNewsButton = onView(withId(R.id.delete_news_item_image_view));
        editNewsButton = onView(withId(R.id.edit_news_item_image_view));
        newsDescriptionButton = onView(withId(R.id.view_news_item_image_view));
        newsDescriptionText = onView(withId(R.id.news_item_description_text_view));
        newsList = onView(withId(R.id.news_list_recycler_view));
        deleteMessage = onView(isRoot()).check(matches(withTextOnScreen(DELETE_MESSAGE)));
        okButton = onView(isRoot()).check(matches(withButtonText(OK_BUTTON)));
        cancelButton = onView(isRoot()).check(matches(withButtonText(CANCEL_BUTTON)));
        categoryIcon = onView(allOf(
                withId(R.id.category_icon_image_view),
                withContentDescription(CATEGORY_ICON),
                withText(ICON_TEXT)
        ));
    }

    public void getControlPanel() {controlPanel.check(matches(isDisplayed())); }
    public void clickAddNewsButton() { addNewsButton.perform(click()); }
    public void clickSortNewsButton() { sortNewsButton.perform(click()); }
    public void clickFilterNewsButton() { filterNewsButton.perform(click()); }
    public void checkNewsCard() { newsCard.check(matches(isDisplayed())); }
    public void getNewsItem(String value) { newsItem.check(hasText(value)); }
    public void getNewsCreationDate(String value) { newsCreationDate.check(hasText(value)); }
    public void getNewsPublicationDate(String value) { newsPublicationDate.check(hasText(value)); }
    public void checkNewsStatusActive() { newsStatusActive.check(matches(isDisplayed()));  }
    public void checkNewsStatusNotActive() { newsStatusNotActive.check(matches(isDisplayed())); }
    public void clickDeleteButton() { deleteNewsButton.perform(click()); }
    public void clickEditNewsButton() { editNewsButton.perform(click()); }
    public void clickNewsDescriptionButton() { newsDescriptionButton.perform(click()); }
    public void checkNewsDescriptionText() { newsDescriptionText.check(matches(isDisplayed())); }
    public void getSortingNewsDescending() { newsList.check(isSortedByDate("dd.MM.yyyy", CustomViewAssertion.SortOrder.DESCENDING)); }
    public void getSortingNewsAscending() { newsList.check(isSortedByDate("dd.MM.yyyy", CustomViewAssertion.SortOrder.ASCENDING)); }
    public void checkDeleteMessage() { deleteMessage.check(matches(isDisplayed())); }
    public void clickOkButton() { okButton.perform(click()); }
    public void clickCancelButton() { cancelButton.perform(click()); }
    public void getInitialNewsCount() {
        initialNewsCount = getItemCount(R.id.news_list_recycler_view);
    }
    public void checkNewsCountDecreasedByOne() {
        int expectedCount = initialNewsCount - 1;
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(recyclerVievSizeMatcher(expectedCount)));
    }
    public void checkNewsCountIncreasedByOne() {
        int expectedCount = initialNewsCount + 1;
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(recyclerVievSizeMatcher(expectedCount)));
    }

    public void getSelectCategory() {
        categoryIcon.check(matches(isDisplayed()));
    }





}
