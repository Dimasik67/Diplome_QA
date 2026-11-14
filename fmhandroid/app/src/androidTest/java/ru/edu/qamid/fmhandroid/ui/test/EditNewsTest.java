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
import ru.edu.qamid.fmhandroid.ui.steps.ChangeNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.EditNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 7. Страница 'Панель управления'. Подпункт 7.2. Просмотр, сортировка, редактирование, удаление нововстей. Тестовые сценарии ТС-049 - ТС-051")

public class EditNewsTest {
    AppHomepageStep appHomepageStep = new AppHomepageStep();
    AuthorizationStep authorizationStep = new AuthorizationStep();
    NewspageStep newspageStep = new NewspageStep();
    EditNewsStep editNewsStep = new EditNewsStep();
    ChangeNewsStep changeNewsStep = new ChangeNewsStep();

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
        newspageStep.clickEditNewsButton();
        editNewsStep.openEditNews();
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
    @DisplayName("ТС-049. Просмотр текста новости")
    public void readNewsDescriptionTest() {
        editNewsStep.checkNewsCard();
        editNewsStep.clickNewsDescriptionButton();
        editNewsStep.checkNewsDescriptionText();
    }

    @Test
    @DisplayName("ТС-050. Редактирование новости")
    public void editNewsCategoryTest() {
        editNewsStep.checkNewsCard();
        editNewsStep.clickEditNewsButton();
        changeNewsStep.openChangeNews();
        changeNewsStep.inputNewsItem("Проверка связи, раз-раз-раз");
        changeNewsStep.clickSaveButton();
        editNewsStep.checkNewsCard();
        editNewsStep.checkNewsItem("Проверка связи, раз-раз-раз");
    }

    @Test
    @DisplayName("ТС-051. Изменение статуса активной новости на 'Не активна'")
    public void changeNewsStatusFromActiveToNotActiveTest() {
        editNewsStep.checkNewsCard();
        editNewsStep.clickEditNewsButton();
        changeNewsStep.getSwitcherOff();
        changeNewsStep.clickSaveButton();
        editNewsStep.checkNewsCard();
        editNewsStep.checkNewsStatusNotActive();
    }
}
