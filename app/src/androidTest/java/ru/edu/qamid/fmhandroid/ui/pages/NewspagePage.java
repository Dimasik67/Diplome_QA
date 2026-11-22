package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.checkDisplayedWithRetry;

import androidx.test.espresso.ViewInteraction;

import ru.edu.qamid.R;

public class NewspagePage {
    public ViewInteraction newsPage;
    public ViewInteraction mainMenuButton;
    public ViewInteraction missionButton;
    public ViewInteraction mainMenuElementNews;
    public ViewInteraction mainMenuElementMainPage;
    public ViewInteraction editNewsButton;
    public ViewInteraction filterNewsButton;


    private static final String MENU_ITEM_NEWS = "Новости";
    private static final String MENU_ITEM_MAIN_PAGE = "Главная";


    public NewspagePage() {
        newsPage = onView(withId(R.id.news_list_container));

        mainMenuButton = onView(withId(R.id.main_menu_image_button));

        missionButton = onView(withId(R.id.our_mission_image_button));

        editNewsButton = onView(withId(R.id.news_edit_button));
        filterNewsButton = onView(withId(R.id.news_filter_button));

        mainMenuElementNews = onView(allOf(
                withId(R.id.menu_item_news),
                withText(MENU_ITEM_NEWS)));

        mainMenuElementMainPage = onView(allOf(
                withId(R.id.menu_item_main),
                withText(MENU_ITEM_MAIN_PAGE)));
    }

    public void getNewspagePage() {checkDisplayedWithRetry(newsPage); }
    public void clickMainMenuButton() {mainMenuButton.perform(click()); }
    public void clickMissionButton() {missionButton.perform(click()); }
    public void clickMainMenuElementNews() {
        mainMenuElementNews.check(matches(isDisplayed()));
        mainMenuElementNews.check(matches(isNotEnabled()));
    }
    public void clickMainMenuElementMainPage() {
        mainMenuElementMainPage.check(matches(isDisplayed()));
        mainMenuElementMainPage.perform(click());
    }
    public void clickEditNewsButton() { editNewsButton.perform(click()); }
    public void clickFilterNewsButton() { filterNewsButton.perform(click()); }

}
