package ru.edu.qamid.fmhandroid.ui.steps;

import java.util.Calendar;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.fmhandroid.ui.pages.FilterNewsPage;

public class FilterNewsStep {
    private final FilterNewsPage filterNewsPage;

    public FilterNewsStep() {this.filterNewsPage = new FilterNewsPage(); }

    @Step("Проверка перехода на страницу фильтрации")
    public void openFilterNews() { filterNewsPage.getFilterNews(); }

    @Step("Нажать на раскрывающееся меню категорий")
    public void clickCategorySelectButton() {
        filterNewsPage.clickCategorySelectionButton();
        filterNewsPage.getCategoryList();}

    @Step("Выбрать из списка категорию 'Объявления'")
    public void getAutoComplete() { filterNewsPage.getAutoComplete(); }

    @Step("Ввести название категории '{value}'")
    public void categoryInput(String value) { filterNewsPage.categoryInput(value); }

    @Step("Ввести начальную дату '{date}'")
    public void inputStartData(Calendar date) { filterNewsPage.inputStartData(date); }

    @Step("Ввести конечную дату '{date}'")
    public void inputEndData(Calendar date) { filterNewsPage.inputEndData(date); }

    @Step("Нажать кнопку 'Фильтровать'")
    public void clickFilterButton() { filterNewsPage.clickFilterButton(); }

    @Step("Нажать кнопку 'Отмена'")
    public void clickCancelButton() { filterNewsPage.clickCancelButton(); }

    @Step("Сообщение об ошибке")
    public void checkErrorMassage() { filterNewsPage.checkErrorMassage(); }

    @Step("Поле начальной даты пустое")
    public void checkStartDateIsEmpty() {filterNewsPage.checkStartDateIsEmpty();}

    @Step("Поле конечной даты пустое")
    public void checkEndDateIsEmpty() {filterNewsPage.checkEndDateIsEmpty();}

}
