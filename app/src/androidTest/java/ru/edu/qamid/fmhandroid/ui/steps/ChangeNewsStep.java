package ru.edu.qamid.fmhandroid.ui.steps;

import java.util.Calendar;

import ru.edu.qamid.fmhandroid.ui.pages.ChangeNewsPage;

public class ChangeNewsStep {
    private final ChangeNewsPage changeNewsPage;
    public ChangeNewsStep() {this.changeNewsPage = new ChangeNewsPage();}

    public void openChangeNews() {
        AllureStep.run("Открыть страницу редактирования новости", () -> changeNewsPage.openChangePage());
    }


    public void getCategoryList() {
        AllureStep.run("Развернуть список категорий", () -> {
            changeNewsPage.clickSelectionButton();
            changeNewsPage.getCategoryList();
        });
    }

    public void getAutoComplete() {
        AllureStep.run("Выбрать категорию из списка", () -> changeNewsPage.getAutoComplete());
    }

    public void inputNewsItem(String value) {
        AllureStep.run("Ввести заголовок новости " + value, () -> changeNewsPage.inputItemNews(value));
    }

    public void selectPublicationDate(Calendar date) {
        AllureStep.run("Заполнение поля 'Дата публикации' " + date, () -> changeNewsPage.selectPublicationDate(date));
    }
    public void selectPublicationTime(Calendar time) {
        AllureStep.run("Заполнение формы времени " + time, () -> changeNewsPage.selectPublicationTime(time));
    }

    public void inputPublicationTime(String value) {
        AllureStep.run("Заполнение времени вручную " + value, () -> changeNewsPage.inputPublicationTime(value));
    }

    public void getPublicTimeErrorMessage() {
        AllureStep.run("Ошибка неверного формата времени", () -> changeNewsPage.getErrorMessage());
    }

    public void getSwitcherOn() {
        AllureStep.run("Переключение свитчера в статус новости 'Активна'", () -> {
            changeNewsPage.setSwitcherState(true);
            changeNewsPage.checkSwitcherOn();
        });
    }

    public void getSwitcherOff() {
        AllureStep.run("Переключение свитчера в статус новости 'Не активна'", () -> {
            changeNewsPage.setSwitcherState(false);
            changeNewsPage.checkSwitcherOff();
        });
    }

    public void clickSaveButton() {
        AllureStep.run("Нажать кнопку 'Сохранить'", () -> changeNewsPage.clickSaveButton());
    }

    public void clickCancelButton() {
        AllureStep.run("Нажать кнопку 'Отмена'", () -> changeNewsPage.clickCancelButton());
    }

    public void getCancelMessage() {
        AllureStep.run("Предупреждение, что данные не будут сохранены при отмене редактирования", () -> changeNewsPage.getCancelMessage());
    }

    public void clickOkButton() {
        AllureStep.run("Подтвердить отмену редактирования", () -> changeNewsPage.clickOkButton());
    }

    public void clickCancelButton2() {
        AllureStep.run("Вернуться к редактированию", () -> changeNewsPage.clickCancelButton2());
    }

}
