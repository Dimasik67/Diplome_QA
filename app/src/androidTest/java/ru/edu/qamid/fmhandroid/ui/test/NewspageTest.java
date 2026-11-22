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
import ru.edu.qamid.fmhandroid.ui.steps.EditNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.FilterNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;
import ru.edu.qamid.fmhandroid.ui.steps.QuotespageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 4. Страница 'Новости'. Тестовые сценарии ТС-021 - ТС-025")

public class NewspageTest {
    AppHomepageStep appHomepageStep = new AppHomepageStep();
    AuthorizationStep authorizationStep = new AuthorizationStep();
    NewspageStep newspageStep = new NewspageStep();
    QuotespageStep quotespageStep = new QuotespageStep();
    FilterNewsStep filterNewsStep = new FilterNewsStep();
    EditNewsStep editNewsStep = new EditNewsStep();

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
        appHomepageStep.openNewsPage();
        newspageStep.openNewspage();
    }

    @After
    public void tearDown() {
        appHomepageStep.logoutIfLoggedIn();
    }


    @Test
    @DisplayName("ТС-021. Переход на главную страницу приложения через главное меню")
    public void mainMenuElementMainPageTest() {
        newspageStep.openMainPage();
        appHomepageStep.checkHomePageImage();
    }

    @Test
    @DisplayName("ТС-022. Переход на страницу 'Цитаты' через кнопку на панели управления приложением")
    public void openQuotesTest() {
        newspageStep.clickMissionButton();
        quotespageStep.openQuotespage();
    }

    @Test
    @DisplayName("ТС-023. Переход на страницу 'Новости' через главное меню")
    public void mainMenuElementNewsIsNotActiveTest() {
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-024. Работа кнопки 'Фильтр'")
    public void openFilterNewsTest() {
        newspageStep.clickFilterNewsButton();
        filterNewsStep.openFilterNews();
    }

    @Test
    @DisplayName("ТС-025. Работа кнопки 'Редактирование'")
    public void openEditNewsTest() {
        newspageStep.clickEditNewsButton();
        editNewsStep.openEditNews();
    }
}
