package ru.edu.qamid.fmhandroid.ui.steps;

import ru.edu.qamid.fmhandroid.ui.pages.NewspagePage;

public class NewspageStep {
    private final NewspagePage newspagePage;

    public NewspageStep() {this.newspagePage = new NewspagePage(); }

    public void openNewspage() {
        AllureStep.run("Проверка перехода на страницу Новости", () -> newspagePage.getNewspagePage());
    }

    public void openMainPage() {
        AllureStep.run("Переход на главную страницу приложения через кнопку главного меню", () -> {
            newspagePage.clickMainMenuButton();
            newspagePage.clickMainMenuElementMainPage();
        });
    }

    public void openNewsPage() {
        AllureStep.run("Переход на страницу 'Новости' через кнопку главного меню", () -> {
            newspagePage.clickMainMenuButton();
            newspagePage.clickMainMenuElementNews();
        });
    }

    public void clickMissionButton() {
        AllureStep.run("Переход на страницу 'Цитаты'", () -> newspagePage.clickMissionButton());
    }

    public void clickEditNewsButton() {
        AllureStep.run("Проверка кнопки редактирования новостей", () -> newspagePage.clickEditNewsButton());
    }

    public void clickFilterNewsButton() {
        AllureStep.run("Проверка кнопки фильтрации новостей", () -> newspagePage.clickFilterNewsButton());
    }



}
