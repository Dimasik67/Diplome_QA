package ru.edu.qamid.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;


import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.waitDisplayed;

import android.view.View;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.pages.AuthorizationPage;


public class AuthorizationStep {

    public AuthorizationPage authorizationPage;


    public AuthorizationStep() {
        this.authorizationPage = new AuthorizationPage();
    }

    @Step("Открыть страницу авторизации")
    public void loadAuthorizationPage() {
        onView(isRoot()).perform(waitDisplayed(R.id.login_edit_text, 20000));
        authorizationPage.getAuthPage();
    }

    @Step("Ввести логин '{value}'")
    public void inputLogin(String value) {
        authorizationPage.inputLogin(value);
    }

    @Step("Ввести пароль '{value}'")
    public void inputPassword(String value) {
        authorizationPage.inputPassword(value);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        authorizationPage.clickLoginButton();
    }

    @Step("Проверить первое сообщение об ошибке")
    public void checkErrorMessage1(View decorView) {
        authorizationPage.checkHasErrorMessage1(decorView);
    }

    @Step("Проверить второе сообщение об ошибке")
    public void checkErrorMessage2(View decorView) {
        authorizationPage.checkHasErrorMessage2(decorView);
    }

    @Step("Ввести валидный логин")
    public String getValidLogin() {
        return authorizationPage.getValidLogin();
    }

    @Step("Ввести валидный пароль")
    public String getValidPassword() {
        return authorizationPage.getValidPassword();
    }

    @Step("Проверить, отображается ли заголовок 'Авторизация'")
    public void checkAuthTitleIsDisplayed() {
        authorizationPage.checkAuthTitleIsDisplayed();
    }
}