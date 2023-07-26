package ru.maruspim.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

@Feature("Checking MZTA website")
@Owner("Mary Pimenova")
public class MZTAWebsiteTests extends TestBase {

    @BeforeEach
    void openMainPage() {

        String pageUrl = "/";

        step("Open MZTA main page: ", () ->
            mainPage.openPage(pageUrl));
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking the header of the opened main page")
    void mainPageHeaderTest() {
        step("Check the header of the main page: ", () ->
                mainPage.checkOpenedPageHeader("Московский завод тепловой автоматики"));
    }

    @ValueSource(strings = {"* Доступно"})
    @ParameterizedTest(name = "Entered login/email are valid")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Successful fulfilling of the registration form.")
    void successfulFillFormTest(String expectedAvailableMessage) {
        String
                name = randomUtils.createRandomUserName(),
                login = randomUtils.createRandomUserLogin(),
                password = randomUtils.createRandomUserPassword(),
                email = randomUtils.createRandomUserEmail();

        step("Open and check the registration form with valid data: ", () -> {
            mainPage.openRegistrationForm("  Регистрация ");
            registrationPage.setUserName(name)
                    .setUserLogin(login)
                    .checkLoginMessageBoxContent(expectedAvailableMessage)
                    .setUserPassword(password, password)
                    .setUserEmail(email)
                    .checkEmailMessageBoxContent(expectedAvailableMessage)
                    .clickOnSubscriptionCheckbox()
                    .checkAntiSpam();
        });
    }

    @CsvFileSource(resources = "/csvFiles/wrongEmailValidation.csv", delimiter = '|')
    @ParameterizedTest(name = "An error message is displayed for the entered e-mail: {0}")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Set invalid e-mail.")
    void emailErrorMessagePresenceTest(String userEmail, String expectedEmailErrorMessage) {
        step("Open and check the registration form with invalid e-mail: ", () -> {
            mainPage.openRegistrationForm("  Регистрация ");
            registrationPage.setUserEmail(userEmail)
                    .checkEmailMessageBoxContent(expectedEmailErrorMessage);
        });
    }

    @CsvFileSource(resources = "/csvFiles/wrongLoginValidation.csv", delimiter = '|')
    @ParameterizedTest(name = "An error message is displayed for the entered login: {0}")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Set invalid login.")
    void loginErrorMessagePresenceTest(String userLogin, String expectedLoginErrorMessage) {
        step("Open and check the registration form with invalid (already taken) login: ", () -> {
            mainPage.openRegistrationForm("  Регистрация ");
            registrationPage.setUserLogin(userLogin)
                    .checkLoginMessageBoxContent(expectedLoginErrorMessage);
        });
    }

    @CsvFileSource(resources = "/csvFiles/wrongPasswordValidation.csv", delimiter = '|')
    @ParameterizedTest(name = "Error messages are displayed for the entered passwords")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Set invalid password.")
    void passwordErrorMessagePresenceTest(String userPassword, String userRepeatedPassword, String expectedPasswordErrorMessage, String expectedPasswordRepeatedErrorMessage) {
        step("Open and check the registration form with invalid password: ", () -> {
            mainPage.openRegistrationForm("  Регистрация ");
            registrationPage.setUserPassword(userPassword, userRepeatedPassword)
                    .checkPasswordMessageBoxContent(expectedPasswordErrorMessage, expectedPasswordRepeatedErrorMessage);
        });
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking drop-down of the list elements in the main menu")
    void mainMenuItemsContentTest() {
        step("Check the elements of the main menu: ", () -> {
            mainPage.checkMenuItemsDroppingDown()
                    .checkElementsOfDropDownList("О компании", "Статьи")
                    .checkMenuItemsContent("Партнерам", "орзина");
        });
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking downloading files from the web-site")
    void filesContentTest() {
        step("Check downloading PDF and XLS files from the web-site: ", () -> {
            downloadPage.parseTestForPDF()
                    .parseTestForXLS();
        });
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking shopping cart filling with Display Module")
    void cartFillingWithDisplayModuleTest() {
        step("Check cart filling with Display Module: ", () -> {
            mainPage.acceptCookies()
                    .openProductionPage("Продукция");
            productionPage.selectDisplayModuleItem()
                    .checkProductListHeader("kB.D - Дисплейные модули")
                    .pressBuyButton()
                    .pressAddItemToCartButton();
            sleep(1);
        });
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking shopping cart filling with Relay Module")
    void cartFillingWithRelayModuleTest() {
        step("Check cart filling with Relay Module: ", () -> {
            mainPage.openProductionPage("Продукция");
            productionPage.selectRelayModuleItem()
                    .checkProductListHeader("MR8 - Модули релейные")
                    .pressBuyButton()
                    .pressAddItemToCartButton();
            sleep(1);
        });
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking shopping cart filling with PTK Module")
    void cartFillingWithPTKModuleTest() {
        step("Check cart filling with PTK Module: ", () -> {
            mainPage.openProductionPage("Продукция");
            productionPage.selectPTKModuleItem()
                    .checkProductListHeader("Субмодули для ПТК КОНТАР")
                    .pressBuyButton()
                    .pressAddItemToCartButton();
            sleep(1);
        });
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking shopping cart content")
    void cartContentTest() {
        step("Check shopping cart content: ", () -> {
            mainPage.openShoppingCart("Корзина");
            cartPage.checkProductsInCart("Список товаров");
        });
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking search by the article")
    void searchByArticleInputTest() {
        step("Check search by article in the product section: ", () -> {
            mainPage.openProductionPage("Продукция")
                    .searchByArticle("гЕ3035127-01", "kB.DIO - Модули расширения с цифровыми каналами");
        });
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    @DisplayName("Checking search by the name")
    void searchByNameInputTest() {
        step("Check search by name in the product section: ", () -> {
            mainPage.openProductionPage("Продукция")
                    .searchByName("kB.TB", "kB.TB - Коннекторный блок");
        });
    }
}
