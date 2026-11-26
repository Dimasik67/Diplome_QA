package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.closeSoftKeyboardIfVisible;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.checkDisplayedWithRetry;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.clickView;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonId;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;

import androidx.test.espresso.ViewInteraction;

import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.data.ToastRecorder;

public class AuthorizationPage {
    private final ViewInteraction loginInput;
    private final ViewInteraction passwordInput;
    private final ViewInteraction loginButton;
    private final ViewInteraction authTitle;
    private final ViewInteraction splashScreen;

    private static final String AUTH_TITLE_TEXT = "Авторизация";
    private static final String VALID_LOGIN = "login2";
    private static final String VALID_PASSWORD = "password2";
    private static final String ERROR_MESSAGE_EMPTY_FIELDS = "Логин и пароль не могут быть пустыми";
    private static final String ERROR_MESSAGE_GENERAL_ERROR = "Что-то пошло не так. Попробуйте позднее.";
    private static final String LOGIN_BUTTON = "Войти";

    public  AuthorizationPage() {
        loginInput = onView(withId(R.id.login_edit_text));
        passwordInput = onView(withId(R.id.password_edit_text));
        loginButton = onView(allOf(withButtonId(R.id.enter_button), withButtonText(LOGIN_BUTTON)));
        authTitle = onView(withText(AUTH_TITLE_TEXT));
        splashScreen = onView(withId(R.id.splash_screen_circular_progress_indicator));
    }

    public void inputLogin(String value) {
        loginInput.perform(typeText(value));
        closeSoftKeyboardIfNeeded();
    }

    public void inputPassword(String value) {
        passwordInput.perform(typeText(value));
        closeSoftKeyboardIfNeeded();
    }

    public void clickLoginButton() {
        loginButton.perform(clickView());
    }

    public void checkHasErrorMessage1() {
        ToastRecorder.checkMessage(ERROR_MESSAGE_EMPTY_FIELDS);
    }

    public void checkHasErrorMessage2() {
        ToastRecorder.checkMessage(ERROR_MESSAGE_GENERAL_ERROR);
    }

    public void checkAuthTitleIsDisplayed() {
        checkDisplayedWithRetry(authTitle);
    }

    private void closeSoftKeyboardIfNeeded() {
        closeSoftKeyboardIfVisible();
    }

    public String getValidLogin() {
        return VALID_LOGIN;
    }

    public String getValidPassword() {
        return VALID_PASSWORD;
    }

    public void getAuthPage() {
        checkDisplayedWithRetry(authTitle);
    }
    public void getSplashScreen() {splashScreen.check(matches(isDisplayed()));}
}
