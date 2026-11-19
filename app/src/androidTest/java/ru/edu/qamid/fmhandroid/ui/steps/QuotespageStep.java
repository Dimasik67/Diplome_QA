package ru.edu.qamid.fmhandroid.ui.steps;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.fmhandroid.ui.pages.QuotespagePage;

public class QuotespageStep {
    private final QuotespagePage quotespagePage;
    public QuotespageStep() {this.quotespagePage = new QuotespagePage(); }

    @Step("Проверка перехода на страницу 'Цитаты'")
    public void openQuotespage() { quotespagePage.getQuotespage(); }

    @Step("Проверка количества цитат на странице")
    public void getListOfQuotes() { quotespagePage.getListOfQuotes(); }

    @Step("Переход к цитате №8")
    public void getQuoters8Card() { quotespagePage.getQuotes8Card();}

    @Step("Развернуть карточку с цитатой")
    public void clickExpandQuote8Button() { quotespagePage.clickExpandQuote8Button(); }

    @Step("Прочесть текст цитаты №8")
    public void getQuote8Text() { quotespagePage.getQuote8Text(); }

    @Step ("Нажать на кнопку главного меню")
    public void clickMainMenuButton() { quotespagePage.clickMainMenuButton(); }

    @Step ("Переход на главную страницу приложения через кнопку главного меню")
    public void clickMainMenuElementMainPage() { quotespagePage.clickMainMenuElementMainPage(); }

    @Step("Переход на страницу новостей через кнопку главного меню")
    public void clickMainMenuElementNews() { quotespagePage.clickMainMenuElementNews(); }

}
