package ru.maruspim.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/test.properties",
        "system:properties"
})
public interface SelenoidConfig extends Config {

    @Key("selenoid.login")
    String login();

    @Key("selenoid.password")
    String password();

    @Key("selenoid.remoteURL")
    String remoteUrl();
}