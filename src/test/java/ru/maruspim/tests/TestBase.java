package ru.maruspim.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.maruspim.helpers.Attach;
import ru.maruspim.pages.*;
import ru.maruspim.utils.RandomUtils;

public class TestBase {
    MainPage mainPage = new MainPage();
    DownloadPage downloadPage = new DownloadPage();
    ProductionPage productionPage = new ProductionPage();
    CartPage cartPage = new CartPage();
    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();

    @BeforeAll
    static void beforeAll() {

        Configuration.pageLoadStrategy = "eager";
        String selenoidUrl = System.getProperty("selenoid_url");
        String userLoginPassword = System.getProperty("user_login_password");
        selenoidUrl = selenoidUrl.replaceAll("https://", "");
        Configuration.remote = "https://" + userLoginPassword + "@" + selenoidUrl;
        Configuration.baseUrl = System.getProperty("base_url", "https://www.mzta.ru");

        String[] browser = System.getProperty("browser", "chrome:100.0").split(":");
        Configuration.browser = browser[0];
        Configuration.browserVersion = browser[1];
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true); // активировать потоковое видео при запуске браузера
        capabilities.setCapability("enableVideo", true); // активировать запись видео
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
