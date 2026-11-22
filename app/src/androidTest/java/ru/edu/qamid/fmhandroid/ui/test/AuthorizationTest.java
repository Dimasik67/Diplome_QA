package ru.edu.qamid.fmhandroid.ui.test;


import android.view.View;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
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

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 1. Авторизация пользователя. Тестовые сценарии ТС-001 - ТС-013")

public class AuthorizationTest {
    AuthorizationStep authorizationStep = new AuthorizationStep();
    AppHomepageStep appHomepageStep = new AppHomepageStep();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private View decorView;

    private static final String GLOBAL_RESOURCE_NAME = "GLOBAL";


    @Before
    public void setUp() {
        CountingIdlingResource espressoIdlingResource = new CountingIdlingResource(GLOBAL_RESOURCE_NAME);
        IdlingRegistry.getInstance().register(espressoIdlingResource);
        authorizationStep.loadAuthorizationPage();
        authorizationStep.checkAuthTitleIsDisplayed();
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        IdlingRegistry.getInstance().unregister(espressoIdlingResource);
    }


    @Test
    @DisplayName("ТС-001. Ввод валидных логина и пароля")
    public void usedValidLogAndPassTest() {

        authorizationStep.inputLogin(authorizationStep.getValidLogin());
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        appHomepageStep.loadAppHomepagePage();
        appHomepageStep.checkHomePageImage();
    }

    @Test
    @DisplayName("ТС-002. Поле 'Логин' пустое, ввод валидного пароля")
    public void withoutLoginTest() {
        authorizationStep.inputLogin("");
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage1(decorView);
    }

    @Test
    @DisplayName("ТС-003. Ввод в поле 'Логин' одной буквы, ввод валидного пароля")
    public void oneLetterLogTest() {
        authorizationStep.inputLogin("q");
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage2(decorView);
    }

    @Test
    @DisplayName("ТС-004. Ввод валидного логина заглавными и строчными буквами, ввод валидного пароля")
    public void bigAndSmallLettersTest() {
        authorizationStep.inputLogin("LoGiN2");
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage2(decorView);
    }

    @Test
    @DisplayName("ТС-005. Ввод логина только спецсимволами, ввод валидного пароля")
    public void specialSymbolsLoginTest() {
        authorizationStep.inputLogin("@#$%**^");
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage2(decorView);
    }

    @Test
    @DisplayName("ТС-006. Ввод логина цифрами, ввод валидного пароля")
    public void loginWithOnlyNumbersTest() {
        authorizationStep.inputLogin("54535");
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage2(decorView);
    }

    @Test
    @DisplayName("ТС-007. Ввод валидного логина, поле 'Пароль' пустое")
    public void withoutPassTest() {
        authorizationStep.inputLogin(authorizationStep.getValidLogin());
        authorizationStep.inputPassword("");
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage1(decorView);
    }

    @Test
    @DisplayName("ТС-008. Ввод валидного логина, ввод в поле 'Пароль' одной буквы")
    public void oneLetterPassTest() {
        authorizationStep.inputLogin(authorizationStep.getValidLogin());
        authorizationStep.inputPassword("j");
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage2(decorView);
    }

    @Test
    @DisplayName("ТС-009. Ввод валидного логина, ввод в поле 'Пароль' спецсимволов")
    public void specialSymbolPassTest() {
        authorizationStep.inputLogin(authorizationStep.getValidLogin());
        authorizationStep.inputPassword("&*^%#");
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage2(decorView);
    }

    @Test
    @DisplayName("ТС-010. Ввод валидного логина, ввод в поле 'Пароль' только цифр")
    public void passWithOnlyNumberTest() {
        authorizationStep.inputLogin(authorizationStep.getValidLogin());
        authorizationStep.inputPassword("56512");
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage2(decorView);
    }

    @Test
    @DisplayName("ТС-011. Ввод логина и пароля незарегистрированного пользователя")
    public void useInvalidLogAndPassTest() {
        authorizationStep.inputLogin("log");
        authorizationStep.inputPassword("pass");
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage2(decorView);
    }

    @Test
    @DisplayName("ТС-012. Поля 'Логин' и 'Пароль' пустые")
    public void withoutLogAndPassTest() {
        authorizationStep.inputLogin("");
        authorizationStep.inputPassword("");
        authorizationStep.clickLoginButton();
        authorizationStep.checkErrorMessage1(decorView);
    }

    @Test
    @DisplayName("ТС-013. Выход из приложения")
    public void logOutTest() {
        authorizationStep.inputLogin(authorizationStep.getValidLogin());
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        appHomepageStep.loadAppHomepagePage();
        appHomepageStep.checkHomePageImage();
        appHomepageStep.clickUserAccount();
        appHomepageStep.clickLogoutButton();
        authorizationStep.loadAuthorizationPage();
    }

    @After
    public void tearDown() {
        appHomepageStep.logoutIfLoggedIn();
    }

}