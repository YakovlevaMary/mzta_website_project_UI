package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private SelenideElement formHeaderText = $("[class=uk-text-uppercase]"),
            cookiesAcceptButton = $("[class='button cookie_accept uk-button uk-button-danger']"),
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
            searchInput = $("[class='uk-inline ']").$("[required='required']"),
            headingText = $("[class='uk-h4 uk-heading-bullet']");

    @Step("Open page with url: {pageUrl}")
    public MainPage openPage(String pageUrl) {
        open(pageUrl);

        return this;
    }

    @Step("Check main page header")
    public MainPage checkOpenedPageHeader(String str) {
        formHeaderText.shouldHave(text(str));

        return this;
    }

    @Step("Press access cookies button")
    public MainPage acceptCookies() {
        cookiesAcceptButton.shouldBe(visible).pressEnter();

        return this;
    }

    @Step("Open registration form")
    public MainPage openRegistrationForm(String str) {
        registrationButton.click();
        loginFormText.shouldHave(text(str));
        registrationRef.click();

        return this;
    }

    @Step("Open production page")
    public MainPage openProductionPage(String str) {
        navigationBar.find(byText(str)).hover().click();

        return this;
    }

    @Step("Open shopping cart")
    public MainPage openShoppingCart(String str) {
        cartButton.click();
        headingText.shouldHave(text(str));

        return this;
    }

    @Step("Check the menu items dropping down after mouse hovering")
    public MainPage checkMenuItemsDroppingDown() {
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
    public MainPage checkElementsOfDropDownList(String str1, String str2) {
        navigationBar.find(byText(str1)).hover();
        articlesItemRef.click();
        headingText.shouldHave(text((str2)));

        return this;
    }

    @Step("Open and check content of the main menu items")
    public MainPage checkMenuItemsContent(String str1, String str2) {
        navigationBar.find(byText(str1)).hover();
        partnersItemRef.click();
        headingText.shouldHave(text((str1)));
        cartButton.click();
        headingText.shouldHave(text(str2));

        return this;
    }

    @Step("Check search by article of a product")
    public MainPage searchByArticle(String value, String str) {
        searchInput.setValue(value).pressEnter();
        headingText.shouldHave(text((str)));

        return this;
    }

    @Step("Check search by name of a product")
    public MainPage searchByName(String value, String str) {
        searchInput.setValue(value).pressEnter();
        headingText.shouldHave(text((str)));

        return this;
    }
}
