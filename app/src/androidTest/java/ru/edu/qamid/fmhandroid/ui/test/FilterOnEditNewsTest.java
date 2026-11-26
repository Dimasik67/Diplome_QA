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
import ru.edu.qamid.fmhandroid.ui.steps.FilterOnEditNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 7. Страница 'Панель управления'. Подпункт 7.3. Фильтрация новостей. Тестовые сценарии ТС-052 - ТС-055")

public class FilterOnEditNewsTest extends BaseTest {
    NewspageStep newspageStep = new NewspageStep();
    EditNewsStep editNewsStep = new EditNewsStep();
    AddNewsStep addNewsStep = new AddNewsStep();
    FilterOnEditNewsStep filterOnEditNewsStep = new FilterOnEditNewsStep();

    @Before
    public void openFilterOnEditNewsPage() {
        appHomepageStep.openNewsPage();
        newspageStep.openNewspage();
        newspageStep.clickEditNewsButton();
        editNewsStep.openEditNews();
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
    @DisplayName("ТС-053. Заполнен чекбокс статуса 'Активна'")
    public void checkBoxActiveNewsTest() {
        editNewsStep.filteredNews();
        filterOnEditNewsStep.openFilter();
        filterOnEditNewsStep.clickCheckBoxNotActive();
        filterOnEditNewsStep.clickFilterButton();
        editNewsStep.openEditNews();
        editNewsStep.checkAnyNewsStatusActive();
    }

    @Test
    @DisplayName("ТС-054. Заполнен чекбокс статуса 'Не активна'")
    public void checkBoxNotActiveNewsTest() {
        // На сервере доступны только активные новости, поэтому создаём тестовую неактивную.
        String title = uniqueTitle("Неактивная новость для фильтра");
        editNewsStep.addNews();
        addNewsStep.openAddNews();
        addNewsStep.inputNewsCategory("Массаж");
        addNewsStep.inputNewsItem(title);
        Calendar publicationDate = Calendar.getInstance();
        addNewsStep.selectPublicationDate(publicationDate);
        addNewsStep.selectPublicationTime(publicationDate);
        addNewsStep.inputNewsDescription("Проверка фильтра по статусу");
        addNewsStep.getSwitcherOff();
        addNewsStep.clickSaveButton();
        editNewsStep.openEditNews();

        editNewsStep.filteredNews();
        filterOnEditNewsStep.openFilter();
        filterOnEditNewsStep.clickCheckBoxActive();
        filterOnEditNewsStep.clickFilterButton();
        editNewsStep.openEditNews();
        editNewsStep.checkNewsStatusNotActive(title);
    }

    @Test
    @DisplayName("ТС-055. Работа кнопки 'Отмена'")
    public void cancelButtonTest() {
        editNewsStep.filteredNews();
        filterOnEditNewsStep.openFilter();
        filterOnEditNewsStep.clickCancelButton();
        editNewsStep.openEditNews();
    }
}
