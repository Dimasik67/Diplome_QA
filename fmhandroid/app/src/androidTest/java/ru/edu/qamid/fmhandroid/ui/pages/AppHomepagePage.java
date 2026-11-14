package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.childAtPosition;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonId;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import ru.edu.qamid.fmhandroid.R;

public class AppHomepagePage {
    public ViewInteraction userAccount;
    public ViewInteraction logoutButton;
    public ViewInteraction mainPageImage;
    public ViewInteraction mainMenuButton;
    public ViewInteraction missionButton;
    public ViewInteraction expandNewsButton;
    public ViewInteraction allNews;
    public ViewInteraction mainMenuElementNews;
    public ViewInteraction mainMenuElementMainPage;

    private static final String LOGOUT_BUTTON = "Выйти";
    private static final String ALL_NEWS_TEXT = "Все новости";
    private static final String MENU_ITEM_NEWS = "Новости";
    private static final String MENU_ITEM_MAINPAGE = "Главная";


    public AppHomepagePage() {
        mainPageImage = onView(withId(R.id.container_custom_app_bar_include_on_fragment_main));
        userAccount = onView(
                allOf(
                        childAtPosition(withId(R.id.container_custom_app_bar_include_on_fragment_main), 5),
                        withContentDescription("Авторизация"))
        );
        logoutButton = onView(withText(LOGOUT_BUTTON));
        mainMenuButton = onView(withId(R.id.main_menu_image_button));
        missionButton = onView(withId(R.id.our_mission_image_button));
        expandNewsButton = onView(
                allOf(
                childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 4),
                withId(R.id.expand_material_button))
        );
        allNews = onView(allOf(withId(R.id.all_news_text_view), withContentDescription(ALL_NEWS_TEXT)));

        mainMenuElementNews = onView(allOf(
                withButtonId(R.id.main_menu_image_button),
                withButtonText(MENU_ITEM_NEWS)));
        mainMenuElementAboutApp = onView(allOf(
                withButtonId(R.id.main_menu_image_button),
                withButtonText(MENU_ITEM_ABOUTAPP)));
        mainMenuElementMainPage = onView(allOf(
                withButtonId(R.id.main_menu_image_button),
                withButtonText(MENU_ITEM_MAINPAGE)));
    }



    public void checkHomePageImage() {
        mainPageImage.check(matches(isDisplayed()));
    }

    public void clickUserAccount() {
        userAccount.perform(click());
    }

    public void clickLogoutButton() {
        logoutButton.perform(click());
    }
    public void clickMainMenuButton() {mainMenuButton.perform(click()); }
    public void clickMissionButton() {missionButton.perform(click()); }
    public void clickExpandNewsButton() {expandNewsButton.perform(click()); }
    public void clickAllNews() { allNews.perform(click()); }

    public void clickMainMenuElementNews() {
        mainMenuElementNews.check(matches(isDisplayed()));
        mainMenuElementNews.perform(click());
    }
    public void clickMainMenuElementMainPage() {
        mainMenuElementMainPage.check(matches(isDisplayed()));
        mainMenuElementMainPage.perform((ViewAction) isNotClickable());
    }
}
