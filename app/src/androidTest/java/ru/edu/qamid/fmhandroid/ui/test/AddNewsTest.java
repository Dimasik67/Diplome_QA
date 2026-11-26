package ru.edu.qamid.fmhandroid.ui.test;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.edu.qamid.fmhandroid.ui.steps.AddNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.EditNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 7. Страница 'Панель управления'. Подпункт 7.1. Создание новостей. Тестовые сценарии ТС-038 - ТС-048")

public class AddNewsTest extends BaseTest {
    NewspageStep newspageStep = new NewspageStep();
    EditNewsStep editNewsStep = new EditNewsStep();
    AddNewsStep addNewsStep = new AddNewsStep();

    @Before
    public void openEditNewsPage() {
        appHomepageStep.openNewsPage();
        newspageStep.openNewspage();
        newspageStep.clickEditNewsButton();
        editNewsStep.openEditNews();
        editNewsStep.checkInitialNewsCount();
    }

        @Test
        @DisplayName("ТС-038. Создание активной новости с категорией 'День рождения'")
        public void createBirthdayCategoryNewsTest() {
            String title = uniqueTitle("Поздравляем Петра Степановича!");
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.getCategoryList();
            addNewsStep.getAutoCompleteText();
            addNewsStep.inputNewsItem(title);
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationDate(currentDate);
            addNewsStep.selectPublicationTime(currentDate);
            addNewsStep.inputNewsDescription("Здоровья, долгих лет жизни!");
            addNewsStep.getSwitcherOn();
            addNewsStep.clickSaveButton();
            editNewsStep.openEditNews();
            editNewsStep.checkNewsCountIncreasedByOne();
            editNewsStep.checkNewsItem(title);
            editNewsStep.checkNewsStatusActive(title);

        }
        @Test
        @DisplayName("ТС-039. Создание неактивной новости с вводом категории вручную") // объединён с ТС-041 Ввод категории новости вручную
        public void createNotActiveNewsTest() {
            String title = uniqueTitle("Открыта запись на четверг");
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.inputNewsCategory("Массаж");
            addNewsStep.inputNewsItem(title);
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationDate(currentDate);
            addNewsStep.selectPublicationTime(currentDate);
            addNewsStep.inputNewsDescription("Массажист принимает с 9:00 до 18:00");
            addNewsStep.getSwitcherOff();
            addNewsStep.clickSaveButton();
            editNewsStep.openEditNews();
            editNewsStep.checkNewsCountIncreasedByOne();
            editNewsStep.checkNewsItem(title);
            editNewsStep.checkNewsStatusNotActive(title);
        }

        @Test
        @DisplayName("ТС-040. Пустые поля при создании новости")
        public void emptyFieldsCreatingNewsTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.clickSaveButton();
            addNewsStep.checkEmptyFieldsErrorMessage();
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
            String title = uniqueTitle("Happy BirthDay, Max");
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.getCategoryList();
            addNewsStep.getAutoCompleteText();
            addNewsStep.inputNewsItem(title);
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationDate(currentDate);
            addNewsStep.selectPublicationTime(currentDate);
            addNewsStep.inputNewsDescription("С наилучшими пожеланиями!");
            addNewsStep.getSwitcherOn();
            addNewsStep.clickSaveButton();
            editNewsStep.openEditNews();
            editNewsStep.checkNewsCountIncreasedByOne();
            editNewsStep.checkNewsItem(title);
        }

        @Test
        @DisplayName("ТС-044. Ввод в строке 'Описание' только цифр")
        public void inputNewsDescriptionNumbersOnlyTest() {
            String title = uniqueTitle("Требуется сиделка");
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.inputNewsCategory("Нужна помощь");
            addNewsStep.inputNewsItem(title);
            // Дата должна быть не раньше текущей: DatePicker запрещает прошедшие даты
            Calendar publicationDate = Calendar.getInstance();
            Calendar publicationTime = Calendar.getInstance();
            publicationTime.set(Calendar.HOUR_OF_DAY, 12);
            publicationTime.set(Calendar.MINUTE, 25);
            addNewsStep.selectPublicationDate(publicationDate);
            addNewsStep.selectPublicationTime(publicationTime);
            addNewsStep.inputNewsDescription("1235465");
            addNewsStep.clickSaveButton();
            editNewsStep.openEditNews();
            editNewsStep.checkNewsCountIncreasedByOne();
            editNewsStep.checkNewsItem(title);
        }

        @Test
        @DisplayName("ТС-045. Ввод даты на 2 года вперёд от текущей")
        public void inputPublicationDateTwoYearsForwardFromCurrentTest() {
            String title = uniqueTitle("Праздник в 2028 году");
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.inputNewsCategory("Праздник");
            addNewsStep.inputNewsItem(title);
            Calendar publicationDate = Calendar.getInstance();
            publicationDate.set(2028, Calendar.OCTOBER, 28);
            Calendar publicationTime = Calendar.getInstance();
            publicationTime.set(Calendar.HOUR_OF_DAY, 12);
            publicationTime.set(Calendar.MINUTE, 25);
            addNewsStep.selectPublicationDate(publicationDate);
            addNewsStep.selectPublicationTime(publicationTime);
            addNewsStep.inputNewsDescription("Выходные дни с 21 по 23 февраля");
            addNewsStep.clickSaveButton();
            editNewsStep.openEditNews();
            editNewsStep.checkNewsCountIncreasedByOne();
            editNewsStep.checkNewsItem(title);
        }

        @Test
        @DisplayName("ТС-048. Ввод значений часов или минут, превышающие допустимый формат")// Объединён с ТС-047. Ввод времени публикации с клавиатуры
        public void inputInvalidTimeFormatTest() {
            editNewsStep.addNews();
            addNewsStep.openAddNews();
            addNewsStep.getCategoryList();
            addNewsStep.getAutoCompleteText();
            addNewsStep.inputNewsItem("Поздравляем!");
            Calendar currentDate = Calendar.getInstance();
            addNewsStep.selectPublicationDate(currentDate);
            addNewsStep.inputInvalidTimeFormat("25:48");
            // Приложение не показывает отдельное сообщение о некорректном времени:
            // проверяем, что поле приняло введённое значение
            addNewsStep.checkTimeFieldValue("25:48");
        }

    }

