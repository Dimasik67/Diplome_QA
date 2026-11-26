package ru.edu.qamid.fmhandroid.ui.test;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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

@Epic("Блок 2. Главная страница приложения. Тестовые сценарии ТС-014 - ТС-017")

public class AppHomePageTest extends BaseTest {
    NewspageStep newspageStep = new NewspageStep();
    QuotespageStep quotespageStep = new QuotespageStep();

    @Test
    @DisplayName("ТС-014. Переход с главной страницы приложения в раздел 'Новости' через выпадающее меню 'Новости'")
    public void expandNewsTest() {
        appHomepageStep.clickExpandNewsButton();
        appHomepageStep.clickAllNews();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-015. Переход с главной страницы на страницу 'Новости' через главное меню")
    public void mainMenuElementNewsTest() {
        appHomepageStep.openNewsPage();
        newspageStep.openNewspage();
    }

    @Test
    @DisplayName("ТС-016. Переход на главную страницу приложения через главное меню")
    public void mainMenuElementMainPageIsNotActiveTest() {
        appHomepageStep.openMainPage();
    }

    @Test
    @DisplayName("ТС-017. Переход в раздел 'Цитаты' через кнопку на панели управления приложения")
    public void openQuotesPageTest() {
        appHomepageStep.clickMissionButton();
        quotespageStep.openQuotespage();
    }
}
