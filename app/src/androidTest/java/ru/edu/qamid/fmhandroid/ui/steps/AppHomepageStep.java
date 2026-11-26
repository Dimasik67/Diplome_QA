package ru.edu.qamid.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.isViewDisplayed;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.waitDisplayed;
import static ru.edu.qamid.fmhandroid.ui.data.DataHelper.waitDisplayedAny;

import ru.edu.qamid.R;
import ru.edu.qamid.fmhandroid.ui.pages.AppHomepagePage;

public class AppHomepageStep {
    private final AppHomepagePage appHomepagePage;
    public AppHomepageStep() {
        this.appHomepagePage = new AppHomepagePage();
    }

    public void loadAppHomepagePage() {
        AllureStep.run("Загрузка главной страницы приложения", () -> {
            onView(isRoot()).perform(waitDisplayed(R.id.main_app_bar, 10000));
            appHomepagePage.checkHomePageImage();
        });
    }

    public void checkHomePageImage() {
        AllureStep.run("Проверить, отображается ли логотип приложения на главной странице", () -> appHomepagePage.checkHomePageImage());
    }

    public boolean isUserAccountDisplayed() {
        return AllureStep.call("Проверить, отображается ли кнопка аккаунта пользователя", () -> appHomepagePage.isUserAccountDisplayed());
    }

    public void clickUserAccount() {
        AllureStep.run("Открыть аккаунт пользователя", () -> appHomepagePage.clickUserAccount());
    }

    public void clickLogoutButton() {
        AllureStep.run("Выйти из приложения", () -> appHomepagePage.clickLogoutButton());
    }

    public void clickExpandNewsButton() {
        AllureStep.run("Развернуть вкладку 'Новости'", () -> appHomepagePage.clickExpandNewsButton());
    }

    public void clickAllNews() {
        AllureStep.run("Нажать на строку 'Все новости'", () -> {
            if (!isViewDisplayed(R.id.all_news_text_view)) {
                clickExpandNewsButton();
            }
            appHomepagePage.clickAllNews();
        });
    }

   public void openNewsPage() {
        AllureStep.run("Переход с главной страницы на страницу 'Новости' через кнопку главного меню", () -> {
            appHomepagePage.clickMainMenuButton();
            appHomepagePage.clickMainMenuElementNews();
        });
    }

    public void openMainPage() {
        AllureStep.run("Переход на главную страницу приложения через главное меню", () -> {
            if (isViewDisplayed(R.id.main_app_bar)) {
                return;
            }
            appHomepagePage.clickMainMenuButton();
            appHomepagePage.clickMainMenuElementMainPage();
        });
    }

    public void clickMissionButton() {
        AllureStep.run("Переход на страницу 'Цитаты'", () -> appHomepagePage.clickMissionButton());
    }

    public void logoutIfLoggedIn() {
        AllureStep.run("Выйти из приложения, если пользователь авторизован", () -> {
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
        });
    }

}
