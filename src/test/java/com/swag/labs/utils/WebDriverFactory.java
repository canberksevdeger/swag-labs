package com.swag.labs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver createWebDriver() {
        String webDriver = System.getProperty("browser", "chrome");
        switch(webDriver) {
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--headless");
                return new ChromeDriver(options);
            default:
                throw new RuntimeException("Unsupported webDriver: " + webDriver);
        }
    }
}
