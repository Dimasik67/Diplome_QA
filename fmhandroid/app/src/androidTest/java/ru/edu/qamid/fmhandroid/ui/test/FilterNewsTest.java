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

import java.util.Calendar;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.edu.qamid.fmhandroid.ui.AppActivity;
import ru.edu.qamid.fmhandroid.ui.steps.AppHomepageStep;
import ru.edu.qamid.fmhandroid.ui.steps.AuthorizationStep;
import ru.edu.qamid.fmhandroid.ui.steps.FilterNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;
import ru.edu.qamid.fmhandroid.ui.steps.QuotespageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 6. Вкладка 'Фильтровать новости' на странице 'Новости'. Тестовые сценарии ТС-026 - ТС-037")

public class FilterNewsTest {
    AppHomepageStep appHomepageStep = new AppHomepageStep();
    AuthorizationStep authorizationStep = new AuthorizationStep();
    NewspageStep newspageStep = new NewspageStep();
    FilterNewsStep filterNewsStep = new FilterNewsStep();
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
        newspageStep.clickFilterNewsButton();
        filterNewsStep.openFilterNews();
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
    @DisplayName("ТС-026. Все поля на странице 'Фильтрация новостей' пустые")
    public void allFilterFieldsEmptyTest() {
        filterNewsStep.categoryInput(" ");
        filterNewsStep.checkStartDateIsEmpty();
        filterNewsStep.checkEndDateIsEmpty();
        filterNewsStep.clickFilterButton();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-027. Выбор категории 'Объявления' из выпадающего списка")
    public void selectAnnouncementCategoryTest() {
        filterNewsStep.clickCategorySelectButton();
        filterNewsStep.getAutoComplete();
        filterNewsStep.clickFilterButton();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-028. Ввод названия категории новостей на кириллице с клавиатуры")
    public void enteringCategoryNameInCyrillicTest() {
        filterNewsStep.categoryInput("Праздник");
        filterNewsStep.clickFilterButton();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-029. Ввод названия категории новостей цифрами с клавиатуры")
    public void enteringCategoryNameInNumbersTest() {
        filterNewsStep.categoryInput("76546");
        filterNewsStep.clickFilterButton();
        filterNewsStep.checkErrorMassage();
    }

    @Test
    @DisplayName("ТС-030. Ввод названия категории новостей специальными символами")
    public void specialSymbolCategoryNameTest() {
        filterNewsStep.categoryInput("#@^&%$");
        filterNewsStep.clickFilterButton();
        filterNewsStep.checkErrorMassage();
    }

    @Test
    @DisplayName("ТС-031. Ввод названия категории новостей на латинице")
    public void enteringCategoryNameInLatinTest() {
        filterNewsStep.categoryInput("BirthDay");
        filterNewsStep.clickFilterButton();
        filterNewsStep.checkErrorMassage();
    }

    @Test
    @DisplayName("ТС-032. Ввод названия категории, отсутствующего в списке")
    public void categoryNameIsNotExistTest() {
        filterNewsStep.categoryInput("Персонал");
        filterNewsStep.clickFilterButton();
        filterNewsStep.checkErrorMassage();
    }

    @Test
    @DisplayName("ТС-033. Заполнение только одного окна с датами - начальная дата")
    public void selectOnlyStartDateTest() {
        filterNewsStep.clickCategorySelectButton();
        filterNewsStep.getAutoComplete();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2026, Calendar.FEBRUARY, 9);
        filterNewsStep.inputStartData(startDate);
        filterNewsStep.checkEndDateIsEmpty();
        filterNewsStep.clickFilterButton();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-034. Заполнение только одного окна с датами - конечная дата")
    public void selectOnlyEndDateTest() {
        filterNewsStep.clickCategorySelectButton();
        filterNewsStep.getAutoComplete();
        filterNewsStep.checkStartDateIsEmpty();
        Calendar endDate = Calendar.getInstance();
        endDate.set(2026, Calendar.FEBRUARY, 12);
        filterNewsStep.inputEndData(endDate);
        filterNewsStep.clickFilterButton();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-035. При заполнении указать конечную дату на несколько лет вперед, начальная дата - текущая")
    public void selectEndDateSeveralYearsForwardFromCurrentDateTest() {
       filterNewsStep.clickCategorySelectButton();
       filterNewsStep.getAutoComplete();
       Calendar startDate = Calendar.getInstance();
       startDate.set(Calendar.HOUR_OF_DAY, 0);
       startDate.set(Calendar.MINUTE, 0);
       startDate.set(Calendar.SECOND, 0);
       startDate.set(Calendar.MILLISECOND, 0);
       Calendar endDate = Calendar.getInstance();
       endDate.set(2030, Calendar.MAY, 1);
       filterNewsStep.inputStartData(startDate);
       filterNewsStep.inputEndData(endDate);
       filterNewsStep.clickFilterButton();
       filterNewsStep.checkErrorMassage();
    }

    @Test
    @DisplayName("ТС-036. При заполнении указать начальную дату на несколько лет вперед, конечная дата - текущая")
    public void SelectStartDateSeveralYearsForwardFromCurrentDateTest() {
        filterNewsStep.clickCategorySelectButton();
        filterNewsStep.getAutoComplete();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2029, Calendar.OCTOBER, 10);
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);
        filterNewsStep.inputStartData(startDate);
        filterNewsStep.inputEndData(endDate);
        filterNewsStep.clickFilterButton();
        filterNewsStep.checkErrorMassage();
    }

    @Test
    @DisplayName("ТС-037. Работа кнопки 'Отмена'")
    public void cancelButtonOperationTest() {
        filterNewsStep.clickCancelButton();
        newspageStep.openNewspage();
    }
}
