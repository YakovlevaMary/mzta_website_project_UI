package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MZTAProductionPageComponent {
    // Selenide elements / locator / etc
    SelenideElement headingText = $("[class='uk-h4 uk-heading-bullet']"),
            productionPage = $("a[href='#sub']"),
            displayModuleRef = $("a[href*='/kb-d']"),
            relayModuleRef = $("a[href*='/mr8']"),
            PTKModuleRef = $("a[href*='/submoduli-kontar']"),
            buyButton = $("[class='uk-button uk-button-danger uk-align-right']"),
            productsSelectionModal = $(".hikashop_products"),
            itemToCartButton = productsSelectionModal.$("ul li:nth-child(2) div div div:nth-child(3) div a");


    // Actions
    @Step("Select item \"Display Module\" on the production page")
    public MZTAProductionPageComponent displayModuleSelecting() {
        displayModuleRef.hover().click();

        return this;
    }

    @Step("Check the header of the opened production section")
    public MZTAProductionPageComponent productListHeaderCheck(String headerText) {
        headingText.shouldHave(text(headerText));

        return this;
    }
    @Step("Press BUY button")
    public MZTAProductionPageComponent pressBuyButton() {
        buyButton.pressEnter();

        return this;
    }
    @Step("Press ADD TO CART button")
    public MZTAProductionPageComponent pressAddItemToCartButton() {
        itemToCartButton.hover().click();

        return this;
    }
    @Step("Add chosen product item \"Relay Module\" to the cart")
    public MZTAProductionPageComponent relayModuleSelecting() {
        relayModuleRef.hover().click();

        return this;
    }
    @Step("Add chosen product item \"PTK Module\" to the cart")
    public MZTAProductionPageComponent ptkModuleSelecting() {
        PTKModuleRef.hover().click();

        return this;
    }
}
