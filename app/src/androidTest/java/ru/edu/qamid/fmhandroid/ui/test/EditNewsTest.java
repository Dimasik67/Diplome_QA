package ru.edu.qamid.fmhandroid.ui.test;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.edu.qamid.fmhandroid.ui.steps.ChangeNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.EditNewsStep;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 7. Страница 'Панель управления'. Подпункт 7.2. Просмотр, сортировка, редактирование, удаление новостей. Тестовые сценарии ТС-049 - ТС-051")

public class EditNewsTest extends BaseTest {
    NewspageStep newspageStep = new NewspageStep();
    EditNewsStep editNewsStep = new EditNewsStep();
    ChangeNewsStep changeNewsStep = new ChangeNewsStep();

    @Before
    public void openEditNewsPage() {
        appHomepageStep.openNewsPage();
        newspageStep.openNewspage();
        newspageStep.clickEditNewsButton();
        editNewsStep.openEditNews();
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
        String title = uniqueTitle("Проверка связи, раз-раз-раз");
        editNewsStep.checkNewsCard();
        editNewsStep.clickEditNewsButton();
        changeNewsStep.openChangeNews();
        changeNewsStep.inputNewsItem(title);
        changeNewsStep.clickSaveButton();
        editNewsStep.checkNewsCard();
        editNewsStep.checkNewsItem(title);
    }

    @Test
    @DisplayName("ТС-051. Изменение статуса активной новости на 'Не активна'")
    public void changeNewsStatusFromActiveToNotActiveTest() {
        String title = uniqueTitle("Статус новости");
        editNewsStep.checkNewsCard();
        editNewsStep.clickEditNewsButton();
        changeNewsStep.openChangeNews();
        changeNewsStep.inputNewsItem(title);
        changeNewsStep.getSwitcherOn();
        changeNewsStep.clickSaveButton();
        editNewsStep.openEditNews();
        editNewsStep.checkNewsItem(title);
        editNewsStep.checkNewsStatusActive(title);

        editNewsStep.clickEditNewsButton(title);
        changeNewsStep.openChangeNews();
        changeNewsStep.getSwitcherOff();
        changeNewsStep.clickSaveButton();
        editNewsStep.openEditNews();
        editNewsStep.checkNewsStatusNotActive(title);
    }
}
