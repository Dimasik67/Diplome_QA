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
import ru.edu.qamid.ui.AppActivity;
import ru.edu.qamid.fmhandroid.ui.steps.AppHomepageStep;
import ru.edu.qamid.fmhandroid.ui.steps.AuthorizationStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;
import ru.edu.qamid.fmhandroid.ui.steps.QuotespageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 2. Главная страница приложения. Тестовые сценарии ТС-014 - ТС-017")

public class AppHomePageTest {
    AppHomepageStep appHomepageStep = new AppHomepageStep();
    AuthorizationStep authorizationStep = new AuthorizationStep();
    NewspageStep newspageStep = new NewspageStep();
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
    }

    @After
    public void tearDown() {
        appHomepageStep.logoutIfLoggedIn();
    }


    @Test
    @DisplayName("ТС-014. Переход с главной страницы приложения в раздел 'Новости' через выпадающее меню 'Новости'")
    public void expandNewsTest() {
        appHomepageStep.clickExpandNewsButton();
        appHomepageStep.clickAllNews();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-015. Переход с главной страницы на страницу 'Новости' через главное меню")
    public void mainMenuElementNewsTest() {
        appHomepageStep.openNewsPage();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-016. Переход на главную страницу приложения через главное меню")
    public void mainMenuElementMainPageIsNotActiveTest() {
        appHomepageStep.openMainPage();
    }

    @Test
    @DisplayName("ТС-017. Переход в раздел 'Цитаты' через кнопку на панели управления приложения")
    public void openQuotesPageTest() {
        appHomepageStep.clickMissionButton();
        quotespageStep.openQuotespage();
    }
}
