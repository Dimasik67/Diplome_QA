package ru.edu.qamid.fmhandroid.ui.steps;

import java.util.Calendar;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.fmhandroid.ui.pages.ChangeNewsPage;

public class ChangeNewsStep {
    private final ChangeNewsPage changeNewsPage;
    public ChangeNewsStep() {this.changeNewsPage = new ChangeNewsPage();}

    @Step("Открыть страницу редактирования новости")
    public void openChangeNews() {changeNewsPage.openChangePage();}


    @Step("Развернуть список категорий")
    public void getCategoryList() {
        changeNewsPage.clickSelectionButton();
        changeNewsPage.getCategoryList();
    }

    @Step("Выбрать категорию из списка")
    public void getAutoComplete() {changeNewsPage.getAutoComplete();}

    @Step("Ввести заголовок новости {'value'}")
    public void inputNewsItem(String value) {changeNewsPage.inputItemNews(value);}

    @Step("Заполнение поля 'Дата публикации' {'date'}")
    public void selectPublicationDate(Calendar date) {changeNewsPage.selectPublicationDate(date);}
    @Step("Заполнение формы времени {'time'}")
    public void selectPublicationTime(Calendar time) {changeNewsPage.selectPublicationTime(time);}

    @Step("Заполнение времени вручную {'value'}")
    public void inputPublicationTime(String value) {changeNewsPage.inputPublicationTime(value);}

    @Step("Ошибка неверного формата времени")
    public void getPublicTimeErrorMessage() { changeNewsPage.getErrorMessage();}

    @Step("Переключение свитчера в статус новости 'Активна'")
    public void getSwitcherOn() {
        changeNewsPage.setSwitcherState(true);
        changeNewsPage.checkSwitcherOn();
    }

    @Step("Переключение свитчера в статус новости 'Не активна'")
    public void getSwitcherOff() {
        changeNewsPage.setSwitcherState(false);
        changeNewsPage.checkSwitcherOff();
    }

    @Step("Нажать кнопку 'Сохранить'")
    public void clickSaveButton() {changeNewsPage.clickSaveButton();}

    @Step("Нажать кнопку 'Отмена'")
    public void clickCancelButton() {changeNewsPage.clickCancelButton();}

    @Step("Предупреждение, что данные не будут сохранены при отмене редактирования")
    public void getCancelMessage() {changeNewsPage.getCancelMessage();}

    @Step("Подтвердить отмену редактирования")
    public void clickOkButton() {changeNewsPage.clickOkButton();}

    @Step("Вернуться к редактированию")
    public void clickCancelButton2() {changeNewsPage.clickCancelButton2();}

}
