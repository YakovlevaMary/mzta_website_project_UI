package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MZTACartPageComponent {

    SelenideElement shopCart= $("[class='hikashop_checkout_cart'] ");
            // shopCart = $("[id='hikashop_checkout_cart_1_1'] table tbody"),
           // product_1 = shopCart.$("tr:nth-child(1)").$("[data-title='Товар']").$("a[href]"),
            //product_2 = shopCart.$("tr:nth-child(2)").$("[data-title='Товар']").$("a[href]"),
            //product_3 = shopCart.$("tr:nth-child(3)").$("[data-title='Товар']").$("a[href]");

    // Actions
    @Step("Check products added to the cart")
    public MZTACartPageComponent productsInCartCheck() {
        shopCart.shouldHave(text("Список товаров"));
        shopCart.shouldHave(text("kB.D-2"));
        shopCart.shouldHave(text("MR8.1121"));
        shopCart.shouldHave(text("MD8.1-01"));
        return this;
    }


}
