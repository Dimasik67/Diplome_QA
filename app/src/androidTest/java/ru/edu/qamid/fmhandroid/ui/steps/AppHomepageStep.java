package ru.edu.qamid.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.isViewDisplayed;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.waitDisplayed;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.waitDisplayedAny;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.pages.AppHomepagePage;

public class AppHomepageStep {
    private final AppHomepagePage appHomepagePage;
    public AppHomepageStep() {
        this.appHomepagePage = new AppHomepagePage();
    }

    @Step("Загрузка главной страницы приложения")
    public void loadAppHomepagePage() {
        onView(isRoot()).perform(waitDisplayed(R.id.main_app_bar, 10000));
        appHomepagePage.checkHomePageImage();
    }

    @Step("Проверить, отображается ли логотип приложения на главной странице")
    public void checkHomePageImage() {
        appHomepagePage.checkHomePageImage();
    }

    @Step("Проверить, отображается ли кнопка аккаунта пользователя")
    public boolean isUserAccountDisplayed() {
        return appHomepagePage.isUserAccountDisplayed();
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

    @Step("Переход на главную страницу приложения через главное меню")
    public void openMainPage() {
        appHomepagePage.clickMainMenuButton();
        appHomepagePage.clickMainMenuElementMainPage();
    }

    @Step("Переход на страницу 'Цитаты'")
    public void clickMissionButton() { appHomepagePage.clickMissionButton();}

    @Step("Выйти из приложения, если пользователь авторизован")
    public void logoutIfLoggedIn() {
        if (appHomepagePage.isMainMenuPopupDisplayed()) {
            appHomepagePage.closeMainMenuPopup();
        }
        // Splash может завершиться как переходом на авторизацию, так и на главную страницу
        // (если сохранена сессия). Дожидаемся одного из этих состояний.
        if (isViewDisplayed(R.id.splashscreen_image_view)) {
            onView(isRoot()).perform(waitDisplayedAny(20000, R.id.login_edit_text, R.id.main_app_bar));
        }
        if (appHomepagePage.isUserAccountDisplayed()) {
            appHomepagePage.clickUserAccount();
            appHomepagePage.clickLogoutButton();
            return;
        }
        // На экране авторизации (или splash) выходить из приложения нельзя:
        // AuthFragment обрабатывает BACK через finishAffinity(), что обрывает instrumentation.
        if (isViewDisplayed(R.id.login_edit_text)
                || isViewDisplayed(R.id.auth_app_bar)
                || isViewDisplayed(R.id.splashscreen_image_view)) {
            return;
        }
    }

}
