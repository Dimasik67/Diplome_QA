package ru.edu.qamid.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonId;
import static ru.edu.qamid.fmhandroid.ui.matchers.CustomViewMatcher.withButtonText;

import androidx.test.espresso.ViewInteraction;

import ru.edu.qamid.fmhandroid.R;

public class AuthorizationPage {
    private final ViewInteraction loginInput;
    private final ViewInteraction passwordInput;
    private final ViewInteraction loginButton;
    private final ViewInteraction errorMessage1;
    private final ViewInteraction errorMessage2;
    private final ViewInteraction authTitle;
    private final ViewInteraction splashScreen;

    private static final String LOGIN_HINT = "Login";
    private static final String PASSWORD_HINT = "Password";
    private static final String AUTH_TITLE_TEXT = "Авторизация";
    private static final String VALID_LOGIN = "login2";
    private static final String VALID_PASSWORD = "password2";
    private static final String ERROR_MESSAGE_EMPTY_FIELDS = "Логин и пароль не могут быть пустыми";
    private static final String ERROR_MESSAGE_GENERAL_ERROR = "Что-то пошло не так. Попробуйте позднее";
    private static final String LOGIN_BUTTON = "Войти";

    public  AuthorizationPage() {
        loginInput = onView(allOf(withId(R.id.login_text_input_layout), withHint(LOGIN_HINT)));
        passwordInput = onView(allOf(withId(R.id.password_text_input_layout), withHint(PASSWORD_HINT)));
        loginButton = onView(allOf(withButtonId(R.id.enter_button), withButtonText(LOGIN_BUTTON)));
        errorMessage1 = onView(withText(ERROR_MESSAGE_EMPTY_FIELDS));
        errorMessage2 = onView(withText(ERROR_MESSAGE_GENERAL_ERROR));
        authTitle = onView(allOf(withId(R.id.nav_host_fragment), withText(AUTH_TITLE_TEXT)));
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
        loginButton.perform(click());
    }

    public void checkHasErrorMessage1() {
        errorMessage1.check(matches(isDisplayed()));
    }

    public void checkHasErrorMessage2() {
        errorMessage2.check(matches(isDisplayed()));
    }

    public void checkAuthTitleIsDisplayed() {
        authTitle.check(matches(isDisplayed()));
    }

    private void closeSoftKeyboardIfNeeded() {
        closeSoftKeyboard();
    }

    public String getValidLogin() {
        return VALID_LOGIN;
    }

    public String getValidPassword() {
        return VALID_PASSWORD;
    }

    public void getAuthPage() {
        authTitle.check(matches(isDisplayed()));
    }
    public void getSplashScreen() {splashScreen.check(matches(isDisplayed()));}
}
