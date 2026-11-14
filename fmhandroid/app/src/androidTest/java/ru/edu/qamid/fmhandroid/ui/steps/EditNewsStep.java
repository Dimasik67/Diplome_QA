package ru.edu.qamid.fmhandroid.ui.steps;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.fmhandroid.ui.pages.EditNewsPage;

public class EditNewsStep {
    private final EditNewsPage editNewsPage;

    public EditNewsStep() {this.editNewsPage = new EditNewsPage(); }

    @Step("Проверка перехода на страницу редактирования новостей")
    public void openEditNews() {editNewsPage.getControlPanel(); }

    @Step("Проверка перехода на страницу создания новостей")
    public void addNews() {editNewsPage.clickAddNewsButton();}

    @Step("Проверка кнопки фильтрации новостей")
    public void filtredNews() {editNewsPage.clickFilterNewsButton();}

    @Step("Сортировка новостей")
    public void sortedNews() {editNewsPage.clickSortNewsButton();}

    @Step("Наличие карточки новости")
    public void checkNewsCard() { editNewsPage.checkNewsCard();}

    @Step("Проверка заголовка новости {'value'}")
    public void checkNewsItem(String value) { editNewsPage.getNewsItem(value);}

    @Step("Проверка даты создания новости {'value'}")
    public void checkNewsCreationData(String value) {editNewsPage.getNewsCreationDate(value);}

    @Step("Проверка даты публикации {'value'}")
    public void checkNewsPublicationDate(String value) {editNewsPage.getNewsPublicationDate(value);}

    @Step("Проверка статуса новости 'Активна'")
    public void checkNewsStatusActive() { editNewsPage.checkNewsStatusActive();}

    @Step("Проверка статуса новости 'Не активна'")
    public void checkNewsStatusNotActive() { editNewsPage.checkNewsStatusNotActive();}

    @Step("Кнопка удаления новости")
    public void clickDeleteButton() {editNewsPage.clickDeleteButton();}

    @Step("Редактирование новости")
    public void clickEditNewsButton() { editNewsPage.clickEditNewsButton();}

    @Step("Развернуть содержание новости")
    public void clickNewsDescriptionButton() { editNewsPage.clickNewsDescriptionButton();}

    @Step("Прочесть содержание новости")
    public void checkNewsDescriptionText() {editNewsPage.checkNewsDescriptionText();}

    @Step("Сортировка новостей от новых к старым")
    public void checkSortingNewsDescending() {editNewsPage.getSortingNewsDescending();}

    @Step("Сортировка новостей от старых к новым")
    public void checkSortingNewsAscending() { editNewsPage.getSortingNewsAscending();}

    @Step("Диалоговое сообщение при удалении новости")
    public void checkDeleteMessage() {editNewsPage.checkDeleteMessage();}

    @Step("Отмена удаления новости")
    public void clickCancelButton() { editNewsPage.clickCancelButton();}

    @Step("Подтверждение удаления новости")
    public void clickOkButton() {editNewsPage.clickOkButton();}

    @Step("Проверка начального количества новостей")
    public void checkInitialNewsCount() { editNewsPage.getInitialNewsCount();}

    @Step("Проверка количества новостей после удаления новости")
    public void checkNewsCountDecreasedByOne() { editNewsPage.checkNewsCountDecreasedByOne();}

    @Step("Проверка количества новостей после добавления новости")
    public void checkNewsCountIncreasedByOne() {editNewsPage.checkNewsCountIncreasedByOne();}

    @Step("Проверка отображения категории новостей после фильтра")
    public void getSelectCategory() {editNewsPage.getSelectCategory();}
}
