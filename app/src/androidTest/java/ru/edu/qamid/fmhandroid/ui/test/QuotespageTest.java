package ru.edu.qamid.fmhandroid.ui.test;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.edu.qamid.fmhandroid.ui.steps.NewspageStep;
import ru.edu.qamid.fmhandroid.ui.steps.QuotespageStep;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Блок 3. Страница 'Цитаты'. Тестовые сценарии ТС-018 - ТС-020")

public class QuotespageTest extends BaseTest {
    NewspageStep newspageStep = new NewspageStep();
    QuotespageStep quotespageStep = new QuotespageStep();

    @Before
    public void openQuotesPage() {
        appHomepageStep.clickMissionButton();
        quotespageStep.openQuotespage();
    }

    @Test
    @DisplayName("ТС-018. Возможность развернуть для чтения цитату на выбор")
    public void expandQuoteTest() {
        quotespageStep.getListOfQuotes();
        quotespageStep.getQuote8Card();
        quotespageStep.clickExpandQuote8Button();
        quotespageStep.getQuote8Text();
    }

    @Test
    @DisplayName("ТС-019. Переход на главную страницу приложения через главное меню")
    public void mainMenuElementMainPageTest() {
        quotespageStep.clickMainMenuButton();
        quotespageStep.clickMainMenuElementMainPage();
        appHomepageStep.checkHomePageImage();
    }

    @Test
    @DisplayName("ТС-020. Переход в раздел 'Новости' через главное меню")
    public void mainMenuElementNewsTest() {
        quotespageStep.clickMainMenuButton();
        quotespageStep.clickMainMenuElementNews();
        newspageStep.openNewspage();
    }
}
