package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private SelenideElement shopCart= $("[class='hikashop_checkout_cart']").$("[class='uk-h4']");

    @Step("Check products added to the cart")
    public CartPage checkProductsInCart(String str) {
        shopCart.shouldHave(text(str));
        return this;
    }


}
