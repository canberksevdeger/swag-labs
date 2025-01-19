package com.swag.labs.pages;

import com.swag.labs.utils.CucumberLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected Boolean waitForVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException ex) {
            CucumberLogger.logger.info(ex);
            return false;
        }
    }

    protected void waitForClickableAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected String getTextOfElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected WebElement getParentElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).findElement(By.xpath("./.."));
    }

    protected WebElement getChildElement(WebElement element, By locator) {
        return wait.until(ExpectedConditions.visibilityOf(element)).findElement(locator);
    }
}
