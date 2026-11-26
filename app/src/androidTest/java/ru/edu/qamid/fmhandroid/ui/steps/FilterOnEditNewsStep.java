package ru.edu.qamid.fmhandroid.ui.steps;

import java.util.Calendar;

import ru.edu.qamid.fmhandroid.ui.pages.FilterOnEditNewsPage;

public class FilterOnEditNewsStep {
    private final FilterOnEditNewsPage filterOnEditNewsPage;
    public FilterOnEditNewsStep() {this.filterOnEditNewsPage = new FilterOnEditNewsPage();}

    public void openFilter() {
        AllureStep.run("Открыть страницу фильтрации новостей", () -> filterOnEditNewsPage.getFilter());
    }

    public void inputCategoryNews(String value) {
        AllureStep.run("Ввести название категории новостей с клавиатуры " + value, () -> filterOnEditNewsPage.inputCategory(value));
    }

    public void getCategoryList() {
        AllureStep.run("Развернуть список категорий", () -> {
            filterOnEditNewsPage.clickSelectButton();
            filterOnEditNewsPage.getCategoryList();
        });
    }

    public void getAutoCompleteText() {
        AllureStep.run("Выбрать категорию из списка", () -> filterOnEditNewsPage.getAutoCompleteCategory());
    }

    public void selectStartDate(Calendar date) {
        AllureStep.run("Заполнить начальную дату " + date, () -> filterOnEditNewsPage.selectStartDate(date));
    }

    public void selectEndDate(Calendar date) {
        AllureStep.run("Заполнить конечную дату " + date, () -> filterOnEditNewsPage.selectEndDate(date));
    }

    public void clickCheckBoxActive() {
        AllureStep.run("Нажать на статус новости 'Активна'", () -> filterOnEditNewsPage.clickCheckBoxActive());
    }

    public void clickCheckBoxNotActive() {
        AllureStep.run("Нажать на статус новости 'Не активна'", () -> filterOnEditNewsPage.clickCheckBoxNotActive());
    }

    public void clickFilterButton() {
        AllureStep.run("Нажать на кнопку 'Фильтровать'", () -> filterOnEditNewsPage.clickFilterButton());
    }

    public void clickCancelButton() {
        AllureStep.run("Нажать на кнопку 'Отмена'", () -> filterOnEditNewsPage.clickCancelButton());
    }
    public void checkStartDateIsEmpty() {
        AllureStep.run("Поле начальной даты пустое", () -> filterOnEditNewsPage.checkStartDateIsEmpty());
    }

    public void checkEndDateIsEmpty() {
        AllureStep.run("Поле конечной даты пустое", () -> filterOnEditNewsPage.checkEndDateIsEmpty());
    }
}
