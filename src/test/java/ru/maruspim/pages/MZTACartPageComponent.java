package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MZTACartPageComponent {

    SelenideElement shopCart= $("[class='hikashop_checkout_cart']").$("[data-title='Товар']");
    // Actions
    @Step("Check products added to the cart")
    public MZTACartPageComponent productsInCartCheck() {
        shopCart.shouldHave(text("Список товаров"));
        return this;
    }


}
