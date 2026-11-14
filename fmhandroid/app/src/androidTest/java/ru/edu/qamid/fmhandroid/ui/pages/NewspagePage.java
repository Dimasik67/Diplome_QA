package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonId;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.AllOf;

import ru.edu.qamid.fmhandroid.R;

public class NewspagePage {
    public ViewInteraction newsPage;
    public ViewInteraction mainMenuButton;
    public ViewInteraction missionButton;
    public ViewInteraction mainMenuElementNews;
    public ViewInteraction mainMenuElementMainPage;
    public ViewInteraction editNewsBotton;
    public ViewInteraction filterNewsButton;


    private static final String MENU_ITEM_NEWS = "Новости";
    private static final String MENU_ITEM_MAINPAGE = "Главная";
    private static final String NEWS_PAGE_TEXT = "Новости";


    public NewspagePage() {
        newsPage = onView(allOf(
                withId(R.id.container_list_news_include),
                withText(NEWS_PAGE_TEXT)
        ));

        mainMenuButton = onView(withId(R.id.main_menu_image_button));

        missionButton = onView(withId(R.id.our_mission_image_button));

        editNewsBotton = onView(allOf(
                withId(R.id.container_list_news_include),
                withButtonId(R.id.edit_news_material_button)
        ));
        filterNewsButton = onView(allOf(
                withId(R.id.container_list_news_include),
                withButtonId(R.id.filter_news_material_button)
        ));

        mainMenuElementNews = onView(AllOf.allOf(
                withButtonId(R.id.main_menu_image_button),
                withButtonText(MENU_ITEM_NEWS)));

        mainMenuElementMainPage = onView(AllOf.allOf(
                withButtonId(R.id.main_menu_image_button),
                withButtonText(MENU_ITEM_MAINPAGE)));
    }

    public void getNewspagePage() {newsPage.check(matches(isDisplayed())); }
    public void clickMainMenuButton() {mainMenuButton.perform(click()); }
    public void clickMissionButton() {missionButton.perform(click()); }
    public void clickMainMenuElementNews() {
        mainMenuElementNews.check(matches(isDisplayed()));
        mainMenuElementNews.perform((ViewAction) isNotClickable());
    }
    public void clickMainMenuElementMainPage() {
        mainMenuElementMainPage.check(matches(isDisplayed()));
        mainMenuElementMainPage.perform(click());
    }
    public void clickEditNewsButton() { editNewsBotton.perform(click()); }
    public void clickFilterNewsButton() { filterNewsButton.perform(click()); }

}
