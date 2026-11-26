package ru.edu.qamid.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.isViewDisplayed;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.waitDisplayedAny;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.data.ToastRecorder;
import ru.edu.qamid.fmhandroid.ui.steps.AppHomepageStep;
import ru.edu.qamid.fmhandroid.ui.steps.AuthorizationStep;
import ru.edu.qamid.ui.AppActivity;

public abstract class BaseTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    protected final AppHomepageStep appHomepageStep = new AppHomepageStep();
    protected final AuthorizationStep authorizationStep = new AuthorizationStep();

    @Before
    public void setUp() {
        ToastRecorder.start();
        onView(isRoot()).perform(waitDisplayedAny(20000, R.id.login_edit_text, R.id.main_app_bar));
        if (isViewDisplayed(R.id.login_edit_text)) {
            authorizationStep.loadAuthorizationPage();
            if (isLoginRequired()) {
                logIn();
            }
            return;
        }

        if (isLoginRequired()) {
            ensureHomePage();
        } else {
            appHomepageStep.logoutIfLoggedIn();
            authorizationStep.loadAuthorizationPage();
        }
    }

    @After
    public void tearDown() {
        ToastRecorder.stop();
    }

    protected boolean isLoginRequired() {
        return true;
    }

    protected void ensureHomePage() {
        if (!isViewDisplayed(R.id.main_app_bar)) {
            appHomepageStep.openMainPage();
        }
        appHomepageStep.loadAppHomepagePage();
        appHomepageStep.checkHomePageImage();
    }

    /**
     * Уникальный заголовок для новостей, создаваемых тестами. Суффикс времени исключает
     * неоднозначность при повторных прогонах на одном и том же сервере.
     */
    protected static String uniqueTitle(String prefix) {
        String suffix = new SimpleDateFormat("HHmmssSSS", Locale.US).format(new Date());
        return prefix + " " + suffix;
    }

    private void logIn() {
        authorizationStep.inputLogin(authorizationStep.getValidLogin());
        authorizationStep.inputPassword(authorizationStep.getValidPassword());
        authorizationStep.clickLoginButton();
        appHomepageStep.loadAppHomepagePage();
        appHomepageStep.checkHomePageImage();
    }
}
