package ru.maruspim.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

public class MZTAWebsiteTests extends TestBase {

    @BeforeEach
    void openMainPage() {

        String pageUrl = "/";

        step("Open MZTA main page: ", () -> {
            mztaMainPageComponent.openPage(pageUrl);

        });
    }

    @Feature("Checking MZTA website")
    @Owner("Mary Pimenova")

    @Test
    @DisplayName("Checking the header of the opened main page")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void mainPageHeaderTest() {

        step("Check the header of the main page: ", () -> {
            mztaMainPageComponent.mainPageHeaderCheck();

        });
    }

    @ValueSource(strings = {"Регистрация", "* Доступно"})
    @ParameterizedTest(name = "Entered login/email are valid")
    @DisplayName("Successful fulfilling of the registration form.")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void successfulFillFormTest(String registrationText, String expectedAvailableMessage) {

        String
                userName = randomUtils.createRandomUserName(),
                userLogin = randomUtils.createRandomUserLogin(),
                userPassword = randomUtils.createRandomUserPassword(),
                userEmail = randomUtils.createRandomUserEmail();

        step("Open and check the registration form with valid data: ", () -> {
            mztaMainPageComponent.openRegistrationForm()
                    .openedPageHeaderCheck(registrationText);
            mztaRegistrationPageComponent.setUserName(userName)
                    .setUserLogin(userLogin)
                    .loginMessageBoxContentChecking(expectedAvailableMessage)
                    .setUserPassword(userPassword, userPassword)
                    .setUserEmail(userEmail)
                    .emailMessageBoxContentChecking(expectedAvailableMessage)
                    .clickOnSubscriptionCheckbox()
                    .antiSpamChecking();

        });
    }

    @CsvFileSource(resources = "/wrongEmailValidation.csv", delimiter = '|')
    @ParameterizedTest(name = "An error message is displayed for the entered e-mail: {0}")
    @DisplayName("Set invalid e-mail.")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void emailErrorMessagePresenceTest(String userEmail, String expectedEmailErrorMessage) {
        step("Open and check the registration form with invalid e-mail: ", () -> {
            mztaMainPageComponent.openRegistrationForm();
            mztaRegistrationPageComponent.setUserEmail(userEmail)
                    .emailMessageBoxContentChecking(expectedEmailErrorMessage);
        });

    }

    @CsvFileSource(resources = "/wrongLoginValidation.csv", delimiter = '|')
    @ParameterizedTest(name = "An error message is displayed for the entered login: {0}")
    @DisplayName("Set invalid login.")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void loginErrorMessagePresenceTest(String userLogin, String expectedLoginErrorMessage) {
        step("Open and check the registration form with invalid (already taken) login: ", () -> {
            mztaMainPageComponent.openRegistrationForm();
            mztaRegistrationPageComponent.setUserLogin(userLogin)
                    .loginMessageBoxContentChecking(expectedLoginErrorMessage);
        });

    }

    @CsvFileSource(resources = "/wrongPasswordValidation.csv", delimiter = '|')
    @ParameterizedTest(name = "Error messages are displayed for the entered passwords")
    @DisplayName("Set invalid password.")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void passwordErrorMessagePresenceTest(String userPassword, String userRepeatedPassword, String expectedPasswordErrorMessage, String expectedPasswordRepeatedErrorMessage) {
        step("Open and check the registration form with invalid password: ", () -> {
            mztaMainPageComponent.openRegistrationForm();
            mztaRegistrationPageComponent.setUserPassword(userPassword, userRepeatedPassword)
                    .passwordMessageBoxContentChecking(expectedPasswordErrorMessage, expectedPasswordRepeatedErrorMessage);
        });

    }

    @CsvFileSource(resources = "/dropDownElements.csv", delimiter = '|')
    @ParameterizedTest(name = "Drop-down of item: {0}")
    @DisplayName("Checking drop-down of the list elements in the main menu")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void mainMenuItemsDropDownTest(String mainMenuItemName, String itemListElementName) {
        step("Check dropping down of the elements in the main menu: ", () -> {
            mztaMainPageComponent.mainMenuItemHover(mainMenuItemName)
                    .elementsDroppingDownNamesCheck(itemListElementName);


        });
    }

    @Test
    @DisplayName("Checking content of the list elements in the main menu")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void mainMenuItemsContentTest() {
        step("Check page content after selecting elements of the main menu: ", () -> {
            mztaMainPageComponent.mainMenuItemHover("О компании")
                    .articlesElementOfDropDownListOpen()
                    .openedPageHeaderCheck("Статьи");

        });
    }

    @Test
    @DisplayName("Checking downloading PDF files from the web-site")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void pdfFilesParsingTest() {
        step("Check downloading PDF files from the web-site: ", () -> {
            mztaDownloadPageComponent.openPriceListPage()
                    .pdfDownloadAndContentCheck();

        });
    }

    @Test
    @DisplayName("Checking downloading XLS files from the web-site")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void xlsFilesParsingTest() {
        step("Check downloading XLS files from the web-site: ", () -> {
            mztaDownloadPageComponent.openPriceListPage()
                    .xlsDownloadAndContentCheck();

        });
    }

    @Test
    @DisplayName("Checking shopping cart filling with Display Module")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void cartFillingWithDisplayModuleTest() {
        step("Check cart filling with Display Module: ", () -> {
            mztaMainPageComponent.acceptCookies()
                    .openProductionPage();
            mztaProductionPageComponent.displayModuleSelecting()
                    .productListHeaderCheck("kB.D - Дисплейные модули")
                    .pressBuyButton()
                    .pressAddItemToCartButton();

        });
    }

    @Test
    @DisplayName("Checking shopping cart filling with Relay Module")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void cartFillingWithRelayModuleTest() {
        step("Check cart filling with Relay Module: ", () -> {
            mztaMainPageComponent.openProductionPage();
            mztaProductionPageComponent.relayModuleSelecting()
                    .productListHeaderCheck("MR8 - Модули релейные")
                    .pressBuyButton()
                    .pressAddItemToCartButton();

        });
    }

    @Test
    @DisplayName("Checking shopping cart filling with PTK Module")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void cartFillingWithPTKModuleTest() {
        step("Check cart filling with PTK Module: ", () -> {
            mztaMainPageComponent.openProductionPage();
            mztaProductionPageComponent.ptkModuleSelecting()
                    .productListHeaderCheck("Субмодули для ПТК КОНТАР")
                    .pressBuyButton()
                    .pressAddItemToCartButton();

        });
    }

    @Test
    @DisplayName("Checking shopping cart content")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void cartContentTest() {
        step("Check shopping cart content: ", () -> {
            mztaMainPageComponent.openShoppingCart()
                    .openedPageHeaderCheck("Корзина");
            mztaCartPageComponent.productsInCartCheck();
        });
    }

    @Test
    @DisplayName("Checking search by the article")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void searchByArticleInputTest() {
        step("Check search by article in the product section: ", () -> {
            mztaMainPageComponent.openProductionPage()
                    .searchInputSetValue("гЕ3035127-01")
                    .openedPageHeaderCheck("kB.DIO - Модули расширения с цифровыми каналами");
        });
    }

    @Test
    @DisplayName("Checking search by the name")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL"),
            @Tag("remote")
    })
    void searchByNameInputTest() {
        step("Check search by name in the product section: ", () -> {
            mztaMainPageComponent.openProductionPage()
                    .searchInputSetValue("kB.TB")
                    .openedPageHeaderCheck("kB.TB - Коннекторный блок");
        });
    }
}
