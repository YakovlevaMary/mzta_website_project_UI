package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSelector;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


public class MZTAProductionPageComponent {
    // Selenide elements / locator / etc
    SelenideElement navigationBar = $("[class='uk-nav uk-navbar-nav uk-flex-wrap']"),
            headingText = $("[class='uk-h4 uk-heading-bullet']"),
            productionPage = $("a[href='#sub']"),
            displayModuleRef = $("a[href*='/kb-d']"),
            relayModuleRef = $("a[href*='/mr8']"),
            PTKModuleRef = $("a[href*='/submoduli-kontar']"),
            buyButton = $("[class='uk-button uk-button-danger uk-align-right']"),
            productsSelectionModal = $(".hikashop_products"),
            displayModuleToCartButton = productsSelectionModal.$("ul li:nth-child(2) div div div:nth-child(3) div a"),
            relayModuleToCartButton = productsSelectionModal.$("ul li:nth-child(5) div div div:nth-child(3) div a"),
            PTKModuleToCartButton = productsSelectionModal.$("ul li:nth-child(7) div div div:nth-child(3) div a");


    // Actions
    @Step("Add chosen product item to the cart")
    public MZTAProductionPageComponent productCartFilling() {
        displayModuleRef.hover().click();
        headingText.shouldHave(text("kB.D - Дисплейные модули"));
        buyButton.pressEnter();
        displayModuleToCartButton.hover().click();
        productionPage.hover().click();
        relayModuleRef.hover().click();
        headingText.shouldHave(text("MR8 - Модули релейные"));
        buyButton.pressEnter();
        relayModuleToCartButton.hover().click();
        productionPage.hover().click();
        PTKModuleRef.hover().click();
        headingText.shouldHave(text("Субмодули для ПТК КОНТАР"));
        buyButton.pressEnter();
        PTKModuleToCartButton.hover().click();

        return this;
    }

}
