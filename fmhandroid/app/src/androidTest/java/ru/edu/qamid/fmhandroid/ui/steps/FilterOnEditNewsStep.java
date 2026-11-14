package ru.edu.qamid.fmhandroid.ui.steps;

import java.util.Calendar;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.fmhandroid.ui.pages.FilterOnEditNewsPage;

public class FilterOnEditNewsStep {
    private final FilterOnEditNewsPage filterOnEditNewsPage;
    public FilterOnEditNewsStep() {this.filterOnEditNewsPage = new FilterOnEditNewsPage();}

    @Step("Открыть страницу фильтрации новостей")
    public void openFilter() {filterOnEditNewsPage.getFilter();}

    @Step("Ввести название категории новостей с клавиатуры {'value'}")
    public void inputCategoryNews(String value) {filterOnEditNewsPage.inputCategory(value);}

    @Step("Развернуть список категорий")
    public void getCategoryList() {
        filterOnEditNewsPage.clickSelectButton();
        filterOnEditNewsPage.getCategoryList();
    }

    @Step("Выбрать категорию из списка")
    public void getAutoCompleteText() {filterOnEditNewsPage.getAutoCompleteCategory();}

    @Step("Заполнить начальную дату {'date'}")
    public void selectStartDate(Calendar date) {filterOnEditNewsPage.selectStartDate(date);}

    @Step("Заполнить конечную дату {'date'}")
    public void selectEndDate(Calendar date) {filterOnEditNewsPage.selectEndDate(date);}

    @Step("Нажать на статус новости 'Активна'")
    public void clickCheckBoxActive() {filterOnEditNewsPage.clickCheckBoxActive();}

    @Step("Нажать на статус новости 'Не активна'")
    public void clickCheckBoxNotActive() {filterOnEditNewsPage.clickCheckBoxNotActive();}

    @Step("Нажать на кнопку 'Фильтровать'")
    public void clickFilterButton() {filterOnEditNewsPage.clickFilterButton();}

    @Step("Нажать на кнопку 'Отмена'")
    public void clickCancelButton() {filterOnEditNewsPage.clickCancelButton();}
    @Step("Поле начальной даты пустое")
    public void checkStartDateIsEmpty() {filterOnEditNewsPage.checkStartDateIsEmpty();}

    @Step("Поле конечной даты пустое")
    public void checkEndDateIsEmpty() {filterOnEditNewsPage.checkEndDateIsEmpty();}
}
