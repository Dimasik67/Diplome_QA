package ru.edu.qamid.fmhandroid.ui.steps;

import ru.edu.qamid.fmhandroid.ui.pages.QuotespagePage;

public class QuotespageStep {
    private final QuotespagePage quotespagePage;
    public QuotespageStep() {this.quotespagePage = new QuotespagePage(); }

    public void openQuotespage() {
        AllureStep.run("Проверка перехода на страницу 'Цитаты'", () -> quotespagePage.getQuotespage());
    }

    public void getListOfQuotes() {
        AllureStep.run("Проверка количества цитат на странице", () -> quotespagePage.getListOfQuotes());
    }

    public void getQuote8Card() {
        AllureStep.run("Переход к цитате №8", () -> quotespagePage.getQuote8Card());
    }

    public void clickExpandQuote8Button() {
        AllureStep.run("Развернуть карточку с цитатой", () -> quotespagePage.clickExpandQuote8Button());
    }

    public void getQuote8Text() {
        AllureStep.run("Прочесть текст цитаты №8", () -> quotespagePage.getQuote8Text());
    }

    public void clickMainMenuButton() {
        AllureStep.run("Нажать на кнопку главного меню", () -> quotespagePage.clickMainMenuButton());
    }

    public void clickMainMenuElementMainPage() {
        AllureStep.run("Переход на главную страницу приложения через кнопку главного меню", () -> quotespagePage.clickMainMenuElementMainPage());
    }

    public void clickMainMenuElementNews() {
        AllureStep.run("Переход на страницу новостей через кнопку главного меню", () -> quotespagePage.clickMainMenuElementNews());
    }

}
