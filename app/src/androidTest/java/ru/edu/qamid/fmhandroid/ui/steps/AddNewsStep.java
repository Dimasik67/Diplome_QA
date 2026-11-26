package ru.edu.qamid.fmhandroid.ui.steps;

import java.util.Calendar;

import ru.edu.qamid.fmhandroid.ui.pages.AddNewsPage;

public class AddNewsStep {
    private final AddNewsPage addNewsPage;
    public AddNewsStep() {this.addNewsPage = new AddNewsPage();}

    public void openAddNews() {
        AllureStep.run("Проверка перехода на страницу создания новости", () -> addNewsPage.openAddNews());
    }

    public void inputNewsCategory(String value) {
        AllureStep.run("Ввод категории новостей вручную " + value, () -> addNewsPage.inputCategoryNews(value));
    }

    public void getCategoryList() {
        AllureStep.run("Развернуть список категорий новостей", () -> {
            addNewsPage.clickCategoryButton();
            addNewsPage.getCategoryList();
        });
    }

    public void getAutoCompleteText() {
        AllureStep.run("Выбрать категорию из списка", () -> addNewsPage.getAutoCompleteText());
    }

    public void inputNewsItem(String value) {
        AllureStep.run("Заполнение строки 'Заголовок' " + value, () -> addNewsPage.inputItemNews(value));
    }

    public void selectPublicationDate(Calendar date) {
        AllureStep.run("Заполнить дату публикации " + date, () -> addNewsPage.selectPublicationDate(date));
    }

    public void selectPublicationTime(Calendar time) {
        AllureStep.run("Заполнить время публикации " + time, () -> addNewsPage.selectPublicationTime(time));
    }

    public void getSwitcherOn() {
        AllureStep.run("Установка статуса новости 'Активна'", () -> {
            addNewsPage.setSwitcherState(true);
            addNewsPage.checkSwitcherOn();
        });
    }
    public void getSwitcherOff() {
        AllureStep.run("Установка статуса новости 'Не активна'", () -> {
            addNewsPage.setSwitcherState(false);
            addNewsPage.checkSwitcherOff();
        });
    }
    public void clickSaveButton() {
        AllureStep.run("Нажать кнопку 'Сохранить'", () -> addNewsPage.clickSaveButton());
    }

    public void clickCancelButton() {
        AllureStep.run("Нажать кнопку 'Отмена'", () -> addNewsPage.clickCancelButton());
    }

    public void checkErrorCategoryLine() {
        AllureStep.run("Ошибка заполнения строки 'Категория'", () -> addNewsPage.checkErrorCategoryIcon());
    }

    public void checkErrorItemLine() {
        AllureStep.run("Ошибка заполнения строки 'Заголовок'", () -> addNewsPage.checkErrorItemIcon());
    }

    public void checkErrorPublicationDate() {
        AllureStep.run("Ошибка заполнения ячейки 'Дата публикации'", () -> addNewsPage.checkErrorDateIcon());
    }

    public void checkErrorPublicationTime() {
        AllureStep.run("Ошибка заполнения ячейки 'Время'", () -> addNewsPage.checkErrorTimeIcon());
    }

    public void checkErrorDescriptionLine() {
        AllureStep.run("Ошибка заполнения строки 'Описание'", () -> addNewsPage.checkErrorDescriptionIcon());
    }

    public void checkTimeErrorMessage() {
        AllureStep.run("Неверный формат времени", () -> addNewsPage.checkErrorMessage());
    }

    public void checkTimeFieldValue(String value) {
        AllureStep.run("Проверка введённого времени " + value, () -> addNewsPage.checkTimeFieldValue(value));
    }
    public void checkPublicationDateIsEmpty() {
        AllureStep.run("Поле даты пустое", () -> addNewsPage.checkPublicationDateIsEmpty());
    }

    public void checkPublicationTimeIsEmpty() {
        AllureStep.run("Поле времени пустое", () -> addNewsPage.checkPublicationTimeIsEmpty());
    }

    public void checkCancelMessage() {
        AllureStep.run("Предупреждение, что данные не будут сохранены при отмене добавления новости", () -> addNewsPage.checkCancelMessage());
    }

    public void clickOkButton() {
        AllureStep.run("Подтвердить отмену добавления новости", () -> addNewsPage.clickOkButton());
    }

    public void clickCancelButton2() {
        AllureStep.run("Вернуться к созданию новости", () -> addNewsPage.clickCancelButton2());
    }

    public void inputNewsDescription(String value) {
        AllureStep.run("Заполнить строку 'Описание' " + value, () -> addNewsPage.inputNewsDescription(value));
    }

    public void checkSaveErrorMessage() {
        AllureStep.run("Ошибка при сохранении новости с несуществующей в списке категорией", () -> addNewsPage.checkSaveErrorMessage());
    }

    public void checkEmptyFieldsErrorMessage() {
        AllureStep.run("Сообщение о незаполненных полях новости", () -> addNewsPage.checkEmptyFieldsErrorMessage());
    }

    public void inputInvalidTimeFormat(String time) {
        AllureStep.run("Ввод некорректного формата времени " + time, () -> addNewsPage.inputInvalidTime(time));
    }

}
