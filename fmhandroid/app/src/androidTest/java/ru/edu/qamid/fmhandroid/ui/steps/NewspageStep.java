package ru.edu.qamid.fmhandroid.ui.steps;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.fmhandroid.ui.pages.NewspagePage;

public class NewspageStep {
    private final NewspagePage newspagePage;

    public NewspageStep() {this.newspagePage = new NewspagePage(); }

    @Step("Проверка перехода на страницу Новости")
    public void openNewspage() {newspagePage.getNewspagePage();}

    @Step("Переход на главную страницу приложения через кнопку главного меню")
    public void openMainPage() {
        newspagePage.clickMainMenuButton();
        newspagePage.clickMainMenuElementMainPage();
    }

    @Step("Переход на страницу 'Новости' через кнопку главного меню")
    public void openNewsPage() {
        newspagePage.clickMainMenuButton();
        newspagePage.clickMainMenuElementNews();
    }

    @Step("Переход на страницу 'Цитаты'")
    public void clickMissionButton() { newspagePage.clickMissionButton(); }

    @Step("Проверка кнопки редактирования новостей")
    public void clickEditNewsButton() { newspagePage.clickEditNewsButton(); }

    @Step("Проверка кнопки фильтрации новостей")
    public void clickFilterNewsButton() { newspagePage.clickFilterNewsButton(); }



}
