package ru.edu.qamid.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.waitDisplayed;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.fmhandroid.R;
import ru.edu.qamid.fmhandroid.ui.pages.AppHomepagePage;

public class AppHomepageStep {
    private final AppHomepagePage appHomepagePage;
    public AppHomepageStep() {
        this.appHomepagePage = new AppHomepagePage();
    }

    @Step("Загрузка главной страницы приложения")
    public void loadAppHomepagePage() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 5000));
        appHomepagePage.checkHomePageImage();
    }

    @Step("Проверить, отображается ли логотип приложения на главной странице")
    public void checkHomePageImage() {
        appHomepagePage.checkHomePageImage();
    }

    @Step("Открыть аккаунт пользователя")
    public void clickUserAccount() {
        appHomepagePage.clickUserAccount();
    }

    @Step("Выйти из приложения")
    public void clickLogoutButton() {
        appHomepagePage.clickLogoutButton();
    }

    @Step("Развернуть вкладку 'Новости'")
    public void clickExpandNewsButton() { appHomepagePage.clickExpandNewsButton(); }

    @Step("Нажать на строку 'Все новости'")
    public void clickAllNews() { appHomepagePage.clickAllNews(); }

   @Step ("Переход с главной страницы на страницу 'Новости' через кнопку главного меню")
    public void openNewsPage() {
        appHomepagePage.clickMainMenuButton();
        appHomepagePage.clickMainMenuElementNews();
    }

    @Step("Переход с главной страницы на страницу 'О приложении' через кнопку главного меню")
    public void openAboutAppPage() {
        appHomepagePage.clickMainMenuButton();
        appHomepagePage.clickMainMenuElementAboutApp();
    }

    @Step("Переход на главную страницу приложения через главное меню")
    public void openMainPage() {
        appHomepagePage.clickMainMenuButton();
        appHomepagePage.clickMainMenuElementMainPage();
    }

    @Step("Переход на страницу 'Цитаты'")
    public void clickMissionButton() { appHomepagePage.clickMissionButton();}

}
