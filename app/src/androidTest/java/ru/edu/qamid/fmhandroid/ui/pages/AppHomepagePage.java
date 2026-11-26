package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.checkDisplayedWithRetry;

import androidx.test.espresso.NoMatchingRootException;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import ru.edu.qamid.R;

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
    private static final String MENU_ITEM_MAIN_PAGE = "Главная";


    public AppHomepagePage() {
        mainPageImage = onView(withId(R.id.main_app_bar));
        userAccount = onView(withId(R.id.authorization_image_button));
        logoutButton = onView(withText(LOGOUT_BUTTON));
        mainMenuButton = onView(withId(R.id.main_menu_image_button));
        missionButton = onView(withId(R.id.our_mission_image_button));
        expandNewsButton = onView(withId(R.id.expand_material_button));
        allNews = onView(allOf(withId(R.id.all_news_text_view), withText(ALL_NEWS_TEXT)));

        mainMenuElementNews = onView(withText(MENU_ITEM_NEWS));
        mainMenuElementMainPage = onView(allOf(
                isNotEnabled(),
                hasDescendant(withText(MENU_ITEM_MAIN_PAGE))
        )).inRoot(isPlatformPopup());
    }



    public void checkHomePageImage() {
        checkDisplayedWithRetry(mainPageImage);
    }

    public boolean isMainMenuPopupDisplayed() {
        try {
            // Не используем inRoot(isPlatformPopup()) для проверки наличия popup:
            // Espresso ждёт появление подходящего root ~30 секунд, если меню закрыто.
            mainMenuElementNews.check(matches(isDisplayed()));
            mainMenuElementMainPage.check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingRootException | NoMatchingViewException | AssertionError ignored) {
            return false;
        }
    }

    public void closeMainMenuPopup() {
        pressBack();
    }

    public boolean isUserAccountDisplayed() {
        try {
            userAccount.check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingRootException | NoMatchingViewException | AssertionError ignored) {
            return false;
        }
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
        checkDisplayedWithRetry(mainMenuElementNews.inRoot(isPlatformPopup()));
        mainMenuElementNews.inRoot(isPlatformPopup()).perform(click());
    }
    public void clickMainMenuElementMainPage() {
        checkDisplayedWithRetry(mainMenuElementMainPage.inRoot(isPlatformPopup()));
        mainMenuElementMainPage.inRoot(isPlatformPopup()).check(matches(isDisplayed()));
        try {
            mainMenuElementMainPage.inRoot(isPlatformPopup()).check(matches(isNotEnabled()));
            pressBack();
        } catch (AssertionError ignored) {
            mainMenuElementMainPage.inRoot(isPlatformPopup()).perform(click());
        }
    }
}
