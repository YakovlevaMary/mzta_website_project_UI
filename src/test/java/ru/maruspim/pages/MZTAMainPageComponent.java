package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.xlstest.XLS;
import com.codeborne.pdftest.PDF;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;


import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MZTAMainPageComponent {
    // Selenide elements / locator / etc
    SelenideElement formHeaderText = $("[class=uk-text-uppercase]"),
            registrationButton = $("[uk-icon=user]"),
            cartButton = $("[uk-icon=cart]"),
            loginFormText = $("#login-form"),
            registrationRef = $("a[href*='/regi?return=aHR0cHM6Ly93d3cubXp0YS5ydS8']"),
            navigationBar = $("[class='uk-nav uk-navbar-nav uk-flex-wrap']"),
            partnersItemRef = $("a[href*='/partneram']"),
            articlesItemRef = $("a[href*='/o-kompanii/stati']"),
            companyItemList = navigationBar.$("li:nth-child(1) div ul"),
            decisionsItemList = navigationBar.$("li:nth-child(3) div ul"),
            servicesItemList = navigationBar.$("li:nth-child(4) div ul"),
            supportItemList = navigationBar.$("li:nth-child(5) div ul"),
            downloadingItemList = navigationBar.$("li:nth-child(6) div ul"),
            partnersItemList = navigationBar.$("li:nth-child(7) div ul"),
            contactsItemList = navigationBar.$("li:nth-child(8) div ul"),
            priceListItemRef = $("a[href*='/skachat/prajs-list']"),
            headingText = $("[class='uk-h4 uk-heading-bullet']");



    // Actions
    @Step("Open page with url: {pageUrl}")
    public MZTAMainPageComponent openPage(String pageUrl) {
        open(pageUrl);
        formHeaderText.shouldHave(text("Московский завод тепловой автоматики"));

        return this;
    }

    @Step("Open registration form")
    public MZTAMainPageComponent openRegistrationForm() {
        registrationButton.click();
        loginFormText.shouldHave(text("  Регистрация "));
        registrationRef.click();
        headingText.shouldHave(text("Регистрация"));

        return this;
    }

    @Step("Open production page")
    public MZTAMainPageComponent openProductionPage() {
        navigationBar.find(byText("Продукция")).click();

        return this;
    }

    @Step("Open shopping cart")
    public MZTAMainPageComponent openShoppingCart() {


        return this;
    }

    @Step("Check the menu items dropping down after mouse hovering")
    public MZTAMainPageComponent menuItemsDroppingDownCheck() {
        navigationBar.find(byText("О компании")).hover();
        companyItemList.$("li:nth-child(10)").shouldHave(text(("Вакансии")));
        navigationBar.find(byText("Решения")).hover();
        decisionsItemList.$("li:nth-child(6)").shouldHave(text(("Инженерные системы")));
        navigationBar.find(byText("Услуги")).hover();
        servicesItemList.$("li:nth-child(3)").shouldHave(text(("Программирование")));
        navigationBar.find(byText("Поддержка")).hover();
        supportItemList.$("li:nth-child(1)").shouldHave(text(("Форум")));
        navigationBar.find(byText("Скачать")).hover();
        downloadingItemList.$("li:nth-child(5)").shouldHave(text(("Сертификаты")));
        navigationBar.find(byText("Партнерам")).hover();
        partnersItemList.$("li:nth-child(3)").shouldHave(text(("Проектировщикам")));
        navigationBar.find(byText("Контакты")).hover();
        contactsItemList.$("li:nth-child(1)").shouldHave(text(("Контакты компании")));

        return this;
    }

    @Step("Open and check the menu items in the drop-down list")
    public MZTAMainPageComponent elementsOfDropDownListCheck() {
        navigationBar.find(byText("О компании")).hover();
        articlesItemRef.click();
        headingText.shouldHave(text(("Статьи")));

        return this;
    }

    @Step("Open and check content of the main menu items ")
    public MZTAMainPageComponent menuItemsContentCheck() {
        navigationBar.find(byText("Партнерам")).hover();
        partnersItemRef.click();
        headingText.shouldHave(text(("Партнерам")));
        cartButton.click();
        headingText.shouldHave(text("Пустая корзина"));
        return this;
    }

}
