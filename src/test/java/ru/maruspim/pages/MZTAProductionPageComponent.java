package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MZTAProductionPageComponent {
    // Selenide elements / locator / etc
    SelenideElement navigationBar = $("[class='uk-nav uk-navbar-nav uk-flex-wrap']"),
            headingText = $("[class='uk-h4 uk-heading-bullet']"),
            displayModuleRef = $("a[href*='/kb-d']"),
            buyButton = $("[class='uk-button uk-button-danger uk-align-right']"),
            productsSelectionModal = $(".hikashop_module");


    // Actions
    @Step("Add chosen product item to the cart")
    public MZTAProductionPageComponent productCartFilling() {
        displayModuleRef.hover().click();
        headingText.shouldHave(text("kB.D - Дисплейные модули"));
        buyButton.pressEnter();
        productsSelectionModal.find(byText("В корзину")).hover().click();
        return this;
    }

}
