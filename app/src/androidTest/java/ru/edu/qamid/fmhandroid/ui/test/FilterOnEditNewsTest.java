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
import ru.edu.qamid.fmhandroid.ui.steps.ChangeNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.EditNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.FilterOnEditNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 7. Страница 'Панель управления'. Подпункт 7.3. Фильтрация новостей. Тестовые сценарии ТС-052 - ТС-055")

public class FilterOnEditNewsTest {
    AppHomepageStep appHomepageStep = new AppHomepageStep();
    AuthorizationStep authorizationStep = new AuthorizationStep();
    NewspageStep newspageStep = new NewspageStep();
    EditNewsStep editNewsStep = new EditNewsStep();
    FilterOnEditNewsStep filterOnEditNewsStep = new FilterOnEditNewsStep();

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
        appHomepageStep.logoutIfLoggedIn();
    }


    @Test
    @DisplayName("ТС-052. Фильтрация новостей по категории 'Массаж'")
    public void filterOnMassageCategoryTest() {
        editNewsStep.filteredNews();
        filterOnEditNewsStep.openFilter();
        filterOnEditNewsStep.getCategoryList();
        filterOnEditNewsStep.getAutoCompleteText();
        filterOnEditNewsStep.clickFilterButton();
        editNewsStep.openEditNews();
        editNewsStep.getSelectCategory();
    }

    @Test
    @DisplayName("ТС-053. Заполнен чек-бокс статуса 'Активна'")
    public void checkBoxActiveNewsTest() {
        editNewsStep.filteredNews();
        filterOnEditNewsStep.openFilter();
        filterOnEditNewsStep.clickCheckBoxNotActive();
        filterOnEditNewsStep.clickFilterButton();
        editNewsStep.openEditNews();
        editNewsStep.checkNewsStatusActive();
    }

    @Test
    @DisplayName("ТС-054. Заполнен чек-бокс статуса 'Не активна'")
    public void checkBoxNotActiveNewsTest() {
        editNewsStep.filteredNews();
        filterOnEditNewsStep.openFilter();
        filterOnEditNewsStep.clickCheckBoxActive();
        filterOnEditNewsStep.clickFilterButton();
        editNewsStep.openEditNews();
        editNewsStep.checkNewsStatusNotActive();
    }

    @Test
    @DisplayName("ТС-055. Работа кнопки 'Отмена'")
    public void cancelButtonTest() {
        editNewsStep.filteredNews();
        filterOnEditNewsStep.clickCancelButton();
        filterOnEditNewsStep.clickCancelButton();
        editNewsStep.openEditNews();
    }
}
