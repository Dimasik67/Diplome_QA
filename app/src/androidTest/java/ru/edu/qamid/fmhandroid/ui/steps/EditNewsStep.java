package ru.edu.qamid.fmhandroid.ui.steps;

import ru.edu.qamid.fmhandroid.ui.pages.EditNewsPage;

public class EditNewsStep {
    private final EditNewsPage editNewsPage;

    public EditNewsStep() {this.editNewsPage = new EditNewsPage(); }

    public void openEditNews() {
        AllureStep.run("Проверка перехода на страницу редактирования новостей", () -> editNewsPage.getControlPanel());
    }

    public void addNews() {
        AllureStep.run("Проверка перехода на страницу создания новостей", () -> editNewsPage.clickAddNewsButton());
    }

    public void filteredNews() {
        AllureStep.run("Проверка кнопки фильтрации новостей", () -> editNewsPage.clickFilterNewsButton());
    }

    public void sortedNews() {
        AllureStep.run("Сортировка новостей", () -> editNewsPage.clickSortNewsButton());
    }

    public void checkNewsCard() {
        AllureStep.run("Наличие карточки новости", () -> editNewsPage.checkNewsCard());
    }

    public void checkNewsItem(String value) {
        AllureStep.run("Проверка заголовка новости " + value, () -> editNewsPage.getNewsItem(value));
    }

    public void checkNewsCreationDate(String value) {
        AllureStep.run("Проверка даты создания новости " + value, () -> editNewsPage.getNewsCreationDate(value));
    }

    public void checkNewsPublicationDate(String value) {
        AllureStep.run("Проверка даты публикации " + value, () -> editNewsPage.getNewsPublicationDate(value));
    }

    public void checkNewsStatusActive() {
        AllureStep.run("Проверка статуса новости 'Активна'", () -> editNewsPage.checkNewsStatusActive());
    }

    public void checkNewsStatusActive(String title) {
        AllureStep.run("Проверка статуса новости 'Активна' " + "{'value'}", () -> editNewsPage.checkNewsStatusActive(title));
    }

    public void checkNewsStatusNotActive() {
        AllureStep.run("Проверка статуса новости 'Не активна'", () -> editNewsPage.checkNewsStatusNotActive());
    }

    public void checkNewsStatusNotActive(String title) {
        AllureStep.run("Проверка статуса новости 'Не активна' " + "{'value'}", () -> editNewsPage.checkNewsStatusNotActive(title));
    }

    public void checkAnyNewsStatusActive() {
        AllureStep.run("Проверка наличия активной новости после фильтрации", () -> editNewsPage.checkAnyNewsStatusActive());
    }

    public void checkAnyNewsStatusNotActive() {
        AllureStep.run("Проверка наличия неактивной новости после фильтрации", () -> editNewsPage.checkAnyNewsStatusNotActive());
    }

    public void clickDeleteButton() {
        AllureStep.run("Кнопка удаления новости", () -> editNewsPage.clickDeleteButton());
    }

    public void clickEditNewsButton() {
        AllureStep.run("Редактирование новости", () -> editNewsPage.clickEditNewsButton());
    }

    public void clickEditNewsButton(String title) {
        AllureStep.run("Редактирование новости " + title, () -> editNewsPage.clickEditNewsButton(title));
    }

    public void clickNewsDescriptionButton() {
        AllureStep.run("Развернуть содержание новости", () -> editNewsPage.clickNewsDescriptionButton());
    }

    public void checkNewsDescriptionText() {
        AllureStep.run("Прочесть содержание новости", () -> editNewsPage.checkNewsDescriptionText());
    }

    public void checkSortingNewsDescending() {
        AllureStep.run("Сортировка новостей от новых к старым", () -> editNewsPage.getSortingNewsDescending());
    }

    public void checkSortingNewsAscending() {
        AllureStep.run("Сортировка новостей от старых к новым", () -> editNewsPage.getSortingNewsAscending());
    }

    public void checkDeleteMessage() {
        AllureStep.run("Диалоговое сообщение при удалении новости", () -> editNewsPage.checkDeleteMessage());
    }

    public void clickCancelButton() {
        AllureStep.run("Отмена удаления новости", () -> editNewsPage.clickCancelButton());
    }

    public void clickOkButton() {
        AllureStep.run("Подтверждение удаления новости", () -> editNewsPage.clickOkButton());
    }

    public void checkInitialNewsCount() {
        AllureStep.run("Проверка начального количества новостей", () -> editNewsPage.getInitialNewsCount());
    }

    public void checkNewsCountDecreasedByOne() {
        AllureStep.run("Проверка количества новостей после удаления новости", () -> editNewsPage.checkNewsCountDecreasedByOne());
    }

    public void checkNewsCountIncreasedByOne() {
        AllureStep.run("Проверка количества новостей после добавления новости", () -> editNewsPage.checkNewsCountIncreasedByOne());
    }

    public void getSelectCategory() {
        AllureStep.run("Проверка отображения категории новостей после фильтра", () -> editNewsPage.getSelectCategory());
    }
}
