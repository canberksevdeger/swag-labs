package com.swag.labs.utils;

import org.openqa.selenium.WebDriver;

import static com.swag.labs.utils.WebDriverFactory.createWebDriver;

public class WebDriverManager {
    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            driver = createWebDriver();
        }
        return driver;
    }

    public void closeDriver() {
        driver.quit();
    }
}
