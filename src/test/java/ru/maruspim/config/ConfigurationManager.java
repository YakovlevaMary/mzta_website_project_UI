package ru.maruspim.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {

    public static WebConfig getWebConfig() {
        return ConfigCache.getOrCreate(WebConfig.class, System.getProperties());
    }

    public static SelenoidConfig getSelenoidConfig() {
        return ConfigCache.getOrCreate(SelenoidConfig.class, System.getProperties());
    }
}