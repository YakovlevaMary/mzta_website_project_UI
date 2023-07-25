package ru.maruspim.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    private SelenideElement userNameInput = $("#user_name"),
            userLoginInput = $("#user_username"),
            userPasswordInput = $("#user_password"),
            userPasswordRepeatInput = $("#user_password2"),
            userEmailInput = $("#user_email"),
            loginContentMessageBox = $(".user_usernameformError .formErrorContent"),
            emailContentMessageBox = $(".user_emailformError .formErrorContent"),
            passwordContentMessageBox = $(".user_passwordformError .formErrorContent"),
            passwordRepeatedContentMessageBox = $(".user_password2formError .formErrorContent"),
            newsSubscription = $("#user_subscription0"),
            antiSpamValue = $(".variation_value"),
            userCaptchaInput = $("#user_captcha");

    @Step("Set user name value")
    public RegistrationPage setUserName(String value) {
        userNameInput.setValue(value).pressTab();

        return this;
    }

    @Step("Set user login value")
    public RegistrationPage setUserLogin(String value) {
        userLoginInput.setValue(value).pressTab();

        return this;
    }

    @Step("Set and repeat user password value")
    public RegistrationPage setUserPassword(String password_1, String password_2) {
        userPasswordInput.setValue(password_1).pressTab();
        userPasswordRepeatInput.setValue(password_2).pressTab();

        return this;
    }

    @Step("Set user e-mail value")
    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value).pressTab();

        return this;
    }

    @Step("Click on news subscription checkbox")
    public RegistrationPage clickOnSubscriptionCheckbox() {
        newsSubscription.click();

        return this;
    }

    @Step("Anti-spam checking")
    public RegistrationPage checkAntiSpam() throws ScriptException {
        String antiSpam = antiSpamValue.getText();
        String[] antiSpamSubstring = antiSpam.split("=");
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Object a = engine.eval(antiSpamSubstring[0]);
        String value = a.toString();
        System.out.println(engine.eval(antiSpamSubstring[0]));
        userCaptchaInput.setValue(value).pressTab();

        return this;
    }

    @Step("Checking the content of the login message box")
    public RegistrationPage checkLoginMessageBoxContent(String expectedMessage) {
        loginContentMessageBox.shouldBe(visible).shouldHave(text(expectedMessage));

        return this;
    }

    @Step("Checking the content of the email message box")
    public RegistrationPage checkEmailMessageBoxContent(String expectedMessage) {
        emailContentMessageBox.shouldBe(visible).shouldHave(text(expectedMessage));

        return this;
    }

    @Step("Checking the content of the password message box")
    public RegistrationPage checkPasswordMessageBoxContent(String expectedMessage_1, String expectedMessage_2) {
        passwordContentMessageBox.shouldBe(visible).shouldHave(text(expectedMessage_1));
        passwordRepeatedContentMessageBox.shouldBe(visible).shouldHave(text(expectedMessage_2));

        return this;
    }
}
