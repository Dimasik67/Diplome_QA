package ru.edu.qamid.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;


import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.waitDisplayed;

import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.pages.AuthorizationPage;


public class AuthorizationStep {

    public AuthorizationPage authorizationPage;


    public AuthorizationStep() {
        this.authorizationPage = new AuthorizationPage();
    }

    public void loadAuthorizationPage() {
        AllureStep.run("Открыть страницу авторизации", () -> {
            onView(isRoot()).perform(waitDisplayed(R.id.login_edit_text, 20000));
            authorizationPage.getAuthPage();
        });
    }

    public void inputLogin(String value) {
        AllureStep.run("Ввести логин '" + value + "'", () -> authorizationPage.inputLogin(value));
    }

    public void inputPassword(String value) {
        AllureStep.run("Ввести пароль '" + value + "'", () -> authorizationPage.inputPassword(value));
    }

    public void clickLoginButton() {
        AllureStep.run("Нажать кнопку 'Войти'", () -> authorizationPage.clickLoginButton());
    }

    public void checkErrorMessage1() {
        AllureStep.run("Проверить первое сообщение об ошибке", () -> authorizationPage.checkHasErrorMessage1());
    }

    public void checkErrorMessage2() {
        AllureStep.run("Проверить второе сообщение об ошибке", () -> authorizationPage.checkHasErrorMessage2());
    }

    public String getValidLogin() {
        return AllureStep.call("Ввести валидный логин", () -> authorizationPage.getValidLogin());
    }

    public String getValidPassword() {
        return AllureStep.call("Ввести валидный пароль", () -> authorizationPage.getValidPassword());
    }

    public void checkAuthTitleIsDisplayed() {
        AllureStep.run("Проверить, отображается ли заголовок 'Авторизация'",
                () -> authorizationPage.checkAuthTitleIsDisplayed());
    }
}
