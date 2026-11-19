package ru.edu.qamid.fmhandroid.ui.steps;

import java.util.Calendar;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.fmhandroid.ui.pages.AddNewsPage;

public class AddNewsStep {
    private final AddNewsPage addNewsPage;
    public AddNewsStep() {this.addNewsPage = new AddNewsPage();}

    @Step("Проверка перехода на страницу создания новости")
    public void openAddNews() {addNewsPage.openAddNews();}

    @Step("Ввод категории новостей вручную {'value'}")
    public void inputNewsCategory(String value) {addNewsPage.inputCategoryNews(value);}

    @Step("Развернуть список категорий новостей")
    public void getCategoryList() {
        addNewsPage.clickCategoryButton();
        addNewsPage.getCategoryList();
    }

    @Step("Выбрать категорию из списка")
    public void getAutoCompleteText() {addNewsPage.getAutoCompleteText();}

    @Step("Заполнение строки 'Заголовок' {'value'}")
    public void inputNewsItem(String value) {addNewsPage.inputItemNews(value);}

    @Step("Заполнить дату публикации {'date'}")
    public void selectPublicationDate(Calendar date) {addNewsPage.selectPublicationDate(date);}

    @Step("Заполнить время публикации {'time'}")
    public void selectPublicationTime(Calendar time) {addNewsPage.selectPublicationTime(time);}

    @Step("Переключение свитчера в статус новости 'Активна'")
    public void getSwitcherOn() {
        addNewsPage.clickSwitcher();
        addNewsPage.checkSwitcherOn();
    }
    @Step("Переключение свитчера в статус новости 'Не активна'")
    public void getSwitcherOff() {
        addNewsPage.clickSwitcher();
        addNewsPage.checkSwitcherOff();
    }
    @Step("Наэать кнопку 'Сохранить'")
    public void clickSaveButton() {addNewsPage.clickSaveButton();}

    @Step("Нажать кнопку 'Отмена'")
    public void clickCancelButton() {addNewsPage.clickCancelButton();}

    @Step("Ошибка заполнения строки 'Категория'")
    public void checkErrorCategoryLine() {addNewsPage.checkErrorCategoryIcon();}

    @Step("Ошибка заполнения строки 'Заголовок'")
    public void checkErrorItemLine() {addNewsPage.checkErrorItemIcon();}

    @Step("Oшибка заполнения ячейки 'Дата публикации'")
    public void checkErrorPublicationDate() {addNewsPage.checkErrorDateIcon();}

    @Step("Ошибка заполнения ячейки 'Время'")
    public void checkErrorPublicationTime() {addNewsPage.checkErrorTimeIcon();}

    @Step("Ошибка заполнения строки 'Описание'")
    public void checkErrorDescriptionLine() {addNewsPage.checkErrorDescriptionIcon();}

    @Step("Неверный формат времени")
    public void checkTimeErrorMessage() {addNewsPage.checkErrorMessage();}
    @Step("Поле даты пустое")
    public void checkPublicationDateIsEmpty() {addNewsPage.checkPublicationDateIsEmpty();}

    @Step("Поле времени пустое")
    public void checkPublicationTimeIsEmpty() {addNewsPage.checkPublicationTimeIsEmpty();}

    @Step("Предупреждение, что данные не будут сохранены при отмене добавления нововсти")
    public void checkCancelMessage() {addNewsPage.checkCancelMessage();}

    @Step("Подтвердить отмену добавления новости")
    public void clickOkButton() {addNewsPage.clickOkButton();}

    @Step("Вернуться к созданию новости")
    public void clickCancelButton2() {addNewsPage.clickCancelButton2();}

    @Step("Заполнить строку 'Описание' {'value'}")
    public void inputNewsDescription(String value) {addNewsPage.inputNewsDescription(value);}

    @Step("Ошибка при сохранении новости с несуществующей в списке категорией")
    public void checkSaveErrorMessage() {addNewsPage.checkSaveErrorMessage();}

    @Step("Ввод некорректного формата времени {'time'}")
    public void inputInvalidTimeFormat(String time) {addNewsPage.inputInvalidTime(time);}

}
