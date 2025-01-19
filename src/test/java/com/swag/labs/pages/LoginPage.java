package com.swag.labs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using="user-name")
    private WebElement userNameTextBox;

    @FindBy(how = How.ID, using="password")
    private WebElement passwordTextBox;

    @FindBy(how = How.ID, using="login-button")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using="[data-test=error]")
    private WebElement errorH3;

    public void enterUsername(String username) {
        userNameTextBox.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordTextBox.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
    public String getLoginErrorMessage(){
        return getTextOfElement(errorH3);
    }
}
