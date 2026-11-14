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
import ru.edu.qamid.fmhandroid.ui.steps.AddNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.AppHomepageStep;
import ru.edu.qamid.fmhandroid.ui.steps.AuthorizationStep;
import ru.edu.qamid.fmhandroid.ui.steps.EditNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;


    @LargeTest
//@RunWith(AndroidJUnit4.class)
    @RunWith(AllureAndroidJUnit4.class)

    @Epic("Блок 7. Страница 'Панель управления'. Подпункт 7.1. Создание новостей. Тестовые сценарии ТС-038- ТС-048")

    public class AddNewsTest {
        AppHomepageStep appHomepageStep = new AppHomepageStep();
        AuthorizationStep authorizationStep = new AuthorizationStep();
        NewspageStep newspageStep = new NewspageStep();
        EditNewsStep editNewsStep = new EditNewsStep();
        AddNewsStep addNewsStep = new AddNewsStep();


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
        @DisplayName("ТС-038. Создание активной новости с категорией 'День рождения'")
        public void createBirthdayCategoryNewsTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.getCategoryList();
            addNewsStep.getAutoCompleteText();
            addNewsStep.inputNewsItem("Поздравляем Петра Степановича!");
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationDate(currentDate);
            addNewsStep.selectPublicationTime(currentDate);
            addNewsStep.inputNewsDescription("Здоровья, долгих лет жизни!");
            addNewsStep.getSwitcherOn();
            addNewsStep.clickSaveButton();
            editNewsStep.openEditNews();
            editNewsStep.checkNewsCountIncreasedByOne();
            editNewsStep.checkNewsItem("Поздравляем Петра Степановича!");
            editNewsStep.checkNewsStatusActive();

        }
        @Test
        @DisplayName("ТС-039. Создание неактивной новости с вводом категории вручную") //объединен с ТС-041 Ввод категории новости вручную
        public void createNotActiveNewsTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.inputNewsCategory("Массаж");
            addNewsStep.inputNewsItem("Открыта запись на четверг");
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationTime(currentDate);
            addNewsStep.selectPublicationTime(currentDate);
            addNewsStep.inputNewsDescription("Массажист принимает с 9:00 до 18:00");
            addNewsStep.getSwitcherOff();
            addNewsStep.clickSaveButton();
            editNewsStep.openEditNews();
            editNewsStep.checkNewsCountIncreasedByOne();
            editNewsStep.checkNewsItem("Открыта запись на четверг");
            editNewsStep.checkNewsStatusNotActive();
        }

        @Test
        @DisplayName("ТС-040. Пустые поля при создании новости")
        public void emptyFieldsCreatingNewsTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.clickSaveButton();
            addNewsStep.checkErrorCategoryLine();
            addNewsStep.checkErrorItemLine();
            addNewsStep.checkErrorPublicationDate();
            addNewsStep.checkErrorPublicationTime();
            addNewsStep.checkErrorDescriptionLine();
        }

        @Test
        @DisplayName("ТС-042. Ввод несуществующего в списке названия категории новостей")
        public void newsCategoryNotExistTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.inputNewsCategory("Персонал");
            addNewsStep.inputNewsItem("Планерка в 9:00");
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationDate(currentDate);
            addNewsStep.selectPublicationTime(currentDate);
            addNewsStep.inputNewsDescription("Отчет за неделю");
            addNewsStep.clickSaveButton();
            addNewsStep.checkSaveErrorMessage();
        }

        @Test
        @DisplayName("ТС-043. Ввод в строке 'Заголовок' текста на латинице")
        public void inputItemNewsOnLatinTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.getCategoryList();
            addNewsStep.getAutoCompleteText();
            addNewsStep.inputNewsItem("Happy BirthDay, Max");
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationDate(currentDate);
            addNewsStep.selectPublicationTime(currentDate);
            addNewsStep.inputNewsDescription("С наилучшими пожеланиями!");
            addNewsStep.getSwitcherOn();
            addNewsStep.clickSaveButton();
            editNewsStep.openEditNews();
            editNewsStep.checkNewsCountIncreasedByOne();
            editNewsStep.checkNewsItem("Happy BirthDay, Max");
        }

        @Test
        @DisplayName("ТС-044. Ввод в строке 'Описание' только цифр")
        public void inputNewsDescriptionNumbersOnlyTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.inputNewsCategory("Нужна помощь");
            addNewsStep.inputNewsItem("Требуется сиделка");
            Calendar publicationDate = Calendar.getInstance();
            publicationDate.set(2026, Calendar.OCTOBER, 28);
            Calendar publicationTime = Calendar.getInstance();
            publicationTime.set(Calendar.HOUR_OF_DAY, 12);
            publicationTime.set(Calendar.MINUTE, 25);
            addNewsStep.selectPublicationDate(publicationDate);
            addNewsStep.selectPublicationTime(publicationTime);
            addNewsStep.inputNewsDescription("1235465");
            addNewsStep.clickSaveButton();
            addNewsStep.checkSaveErrorMessage();
        }

        @Test
        @DisplayName("ТС-045. Ввод даты на 2 года вперед от текущей")
        public void inputPublicationDateTwoYearForwardFromCurrentTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.inputNewsCategory("Праздник");
            addNewsStep.inputNewsItem("Праздник");
            Calendar publicationDate = Calendar.getInstance();
            publicationDate.set(2028, Calendar.OCTOBER, 28);
            Calendar publicationTime = Calendar.getInstance();
            publicationTime.set(Calendar.HOUR_OF_DAY, 12);
            publicationTime.set(Calendar.MINUTE, 25);
            addNewsStep.selectPublicationDate(publicationDate);
            addNewsStep.selectPublicationTime(publicationTime);
            addNewsStep.inputNewsDescription("Выходные дни с 21 по 23 февраля");
            addNewsStep.clickSaveButton();
            addNewsStep.checkSaveErrorMessage();
        }

        @Test
        @DisplayName("ТС-048. Ввод значений часов или минут, превышающие допустимый формат")// Объединен с ТС-047. Ввод времени публикации с клавиатуры
        public void inputInvalidTimeFormatTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.getCategoryList();
            addNewsStep.getAutoCompleteText();
            addNewsStep.inputNewsItem("Поздравляем!");
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationDate(currentDate);
            addNewsStep.inputInvalidTimeFormat("25:48");
            addNewsStep.checkTimeErrorMessage();
        }

    }

