package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MZTAMainPageComponent {
    // Selenide elements / locator / etc
    SelenideElement formHeaderText = $("[class=uk-text-uppercase]"),
            cookiesAcceptButton = $("[class='button cookie_accept uk-button uk-button-danger']"),
            registrationButton = $("[uk-icon=user]"),
            cartButton = $("[uk-icon=cart]"),
            registrationRef = $("a[href*='/regi?return=aHR0cHM6Ly93d3cubXp0YS5ydS8']"),
            navigationBar = $("[class='uk-nav uk-navbar-nav uk-flex-wrap']"),
            articlesItemRef = $("a[href*='/o-kompanii/stati']"),
            itemListElement = navigationBar.$("li:nth-child(1) div ul").$("li:nth-child(2)"),
            searchInput = $("[class='uk-inline ']").$("[required='required']"),
            headingText = $("[class='uk-h4 uk-heading-bullet']");


    // Actions
    @Step("Open page with url: {pageUrl}")
    public MZTAMainPageComponent openPage(String pageUrl) {
        open(pageUrl);

        return this;
    }

    @Step("Check main page header")
    public MZTAMainPageComponent mainPageHeaderCheck() {
        formHeaderText.shouldHave(text("Московский завод тепловой автоматики"));

        return this;
    }

    @Step("Press access cookies button")
    public MZTAMainPageComponent acceptCookies() {
        cookiesAcceptButton.shouldBe(visible).pressEnter();
        return this;
    }

    @Step("Open registration form")
    public MZTAMainPageComponent openRegistrationForm() {
        registrationButton.click();
        registrationRef.click();

        return this;
    }

    @Step("Open production page")
    public MZTAMainPageComponent openProductionPage() {
        navigationBar.find(byText("Продукция")).hover().click();

        return this;
    }

    @Step("Open shopping cart")
    public MZTAMainPageComponent openShoppingCart() {
        cartButton.click();
        return this;
    }

    @Step("Hover over the main menu item")
    public MZTAMainPageComponent mainMenuItemHover(String mainMenuItemName) {
        navigationBar.find(byText(mainMenuItemName)).hover();

        return this;
    }

    @Step("Check the names of the dropped down menu elements")
    public MZTAMainPageComponent elementsDroppingDownNamesCheck(String itemListElementName) {
        itemListElement.shouldHave(text((itemListElementName)));

        return this;
    }

    @Step("Open and check the menu elements in the drop-down list")
    public MZTAMainPageComponent articlesElementOfDropDownListOpen() {
        articlesItemRef.click();

        return this;
    }

    @Step("Check setting search input of a demand product")
    public MZTAMainPageComponent searchInputSetValue(String searchInputValue) {
        searchInput.setValue(searchInputValue).pressEnter();

        return this;
    }

    @Step("Check header of the opened page")
    public MZTAMainPageComponent openedPageHeaderCheck(String headerValue) {
        headingText.shouldHave(text((headerValue)));

        return this;
    }


}
