package ru.edu.qamid.fmhandroid.ui.test;

import android.view.View;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.edu.qamid.fmhandroid.ui.AppActivity;
import ru.edu.qamid.fmhandroid.ui.steps.AppHomepageStep;
import ru.edu.qamid.fmhandroid.ui.steps.AuthorizationStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;
import ru.edu.qamid.fmhandroid.ui.steps.QuotespageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 3. Страница 'Цитаты'. Тестовые сценарии ТС-018 - ТС-020")

public class QuotespageTest {
    AppHomepageStep appHomepageStep = new AppHomepageStep();
    AuthorizationStep authorizationStep = new AuthorizationStep();
    NewspageStep newspageStep = new NewspageStep();
    AboutAppStep aboutAppStep = new AboutAppStep();
    QuotespageStep quotespageStep = new QuotespageStep();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private View decorView;

    private static final String GLOBAL_RESOURCE_NAME = "GLOBAL";

    @Before
    public void logIn() {
        CountingIdlingResource espressoIdlingResource = new CountingIdlingResource(GLOBAL_RESOURCE_NAME);
        IdlingRegistry.getInstance().register(espressoIdlingResource);
        authorizationStep.loadAuthorizationPage();
        authorizationStep.checkAuthTitleIsDisplayed();
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        IdlingRegistry.getInstance().unregister(espressoIdlingResource);
        authorizationStep.inputLogin(authorizationStep.getValidLogin());
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        appHomepageStep.loadAppHomepagePage();
        appHomepageStep.checkHomePageImage();
        appHomepageStep.clickMissionButton();
        quotespageStep.openQuotespage();
    }

    @After
    public void tearDown() {
        logoutIfLoggedIn();
    }

    private void logoutIfLoggedIn() {
        appHomepageStep.clickUserAccount();
        appHomepageStep.clickLogoutButton();
    }

    @Test
    @DisplayName("ТС-018. Возможность развернуть для чтения цитату на выбор")
    public void expandQouteTest() {
        quotespageStep.getListOfQuotes();
        quotespageStep.getQuoters8Card();
        quotespageStep.clickExpandQuote8Button();
        quotespageStep.getQuote8Text();
    }

    @Test
    @DisplayName("ТС-019. Переход на главную страницу приложения через главное меню")
    public void mainMenuElementMainPageTest() {
        quotespageStep.clickMainMenuElementMainPage();
        appHomepageStep.checkHomePageImage();
    }

    @Test
    @DisplayName("ТС-020. Переход в раздел 'Новости' через главное меню")
    public void mainMenuElementNewsTest() {
        quotespageStep.clickMainMenuButton();
        quotespageStep.clickMainMenuElementNews();
        newspageStep.openNewspage();
    }
}
