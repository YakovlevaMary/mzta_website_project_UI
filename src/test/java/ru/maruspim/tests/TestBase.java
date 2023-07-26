package ru.maruspim.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.maruspim.config.SelenoidConfig;
import ru.maruspim.config.WebConfig;
import ru.maruspim.config.ConfigurationManager;
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
    protected static WebConfig webConfig = ConfigurationManager.getWebConfig();
    protected static SelenoidConfig selenoidConfig = ConfigurationManager.getSelenoidConfig();
    protected static boolean isRemote = Boolean.getBoolean("isRemote");

    @BeforeAll
    static void beforeAll() {

        Configuration.pageLoadStrategy = System.getProperty("selenide.pageLoadStrategy", "eager");
        Configuration.baseUrl = webConfig.baseUrl();
        String[] browserAndVersion = webConfig.browserAndVersion();
        Configuration.browser = browserAndVersion[0];
        Configuration.browserVersion = browserAndVersion[1];
        Configuration.browserSize = webConfig.browserSize();

        if (isRemote) {
            String remoteUrl = selenoidConfig.remoteUrl();
            Configuration.remote = "https://" + selenoidConfig.login() + ":" + selenoidConfig.password() + "@" + remoteUrl + "/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        if (isRemote) {
            Attach.addVideo();
        }
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
    }
}
