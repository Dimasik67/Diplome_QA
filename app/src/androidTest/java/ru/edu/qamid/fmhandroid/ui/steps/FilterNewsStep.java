package ru.edu.qamid.fmhandroid.ui.steps;

import java.util.Calendar;

import ru.edu.qamid.fmhandroid.ui.pages.FilterNewsPage;

public class FilterNewsStep {
    private final FilterNewsPage filterNewsPage;

    public FilterNewsStep() {this.filterNewsPage = new FilterNewsPage(); }

    public void openFilterNews() {
        AllureStep.run("Проверка перехода на страницу фильтрации", () -> filterNewsPage.getFilterNews());
    }

    public void clickCategorySelectButton() {
        AllureStep.run("Нажать на раскрывающееся меню категорий", () -> {
            filterNewsPage.clickCategorySelectionButton();
            filterNewsPage.getCategoryList();
        });
    }

    public void getAutoComplete() {
        AllureStep.run("Выбрать из списка категорию 'Объявления'", () -> filterNewsPage.getAutoComplete());
    }

    public void categoryInput(String value) {
        AllureStep.run("Ввести название категории '" + value + "'", () -> filterNewsPage.categoryInput(value));
    }

    public void inputStartDate(Calendar date) {
        AllureStep.run("Ввести начальную дату '" + date + "'", () -> filterNewsPage.inputStartDate(date));
    }

    public void inputEndDate(Calendar date) {
        AllureStep.run("Ввести конечную дату '" + date + "'", () -> filterNewsPage.inputEndDate(date));
    }

    public void clickFilterButton() {
        AllureStep.run("Нажать кнопку 'Фильтровать'", () -> filterNewsPage.clickFilterButton());
    }

    public void clickCancelButton() {
        AllureStep.run("Нажать кнопку 'Отмена'", () -> filterNewsPage.clickCancelButton());
    }

    public void checkErrorMessage() {
        AllureStep.run("Сообщение об ошибке", () -> filterNewsPage.checkErrorMessage());
    }

    public void checkStartDateIsEmpty() {
        AllureStep.run("Поле начальной даты пустое", () -> filterNewsPage.checkStartDateIsEmpty());
    }

    public void checkEndDateIsEmpty() {
        AllureStep.run("Поле конечной даты пустое", () -> filterNewsPage.checkEndDateIsEmpty());
    }

}
