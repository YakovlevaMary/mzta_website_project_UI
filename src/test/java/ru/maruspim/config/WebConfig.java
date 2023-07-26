package ru.maruspim.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/test.properties",
        "system:properties"
})
public interface WebConfig extends Config {

    @DefaultValue("Chrome, 100.0")
    @Key("browserAndVersion")
    String[] browserAndVersion();

    @DefaultValue("1920x1080")
    @Key("browserSize")
    String browserSize();

    @DefaultValue("https://www.mzta.ru")
    @Key("baseURL")
    String baseUrl();

}