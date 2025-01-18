package com.swag.labs.StepDefinition;

import com.swag.labs.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import com.swag.labs.pages.InventoryPage;
import com.swag.labs.pages.LoginPage;
import com.swag.labs.utils.PageObjectManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private WebDriverManager driverManager;
    private PageObjectManager pageObjectManager;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    public LoginSteps(WebDriverManager driverManager) {
        this.driverManager = driverManager;
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
    }

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        driverManager.getDriver().get("https://www.saucedemo.com/");
    }

    @When("{string} and {string} is entered")
    public void username_and_password_is_entered(String username, String password) {
        loginPage = pageObjectManager.getLoginPage();
        loginPage.enter_username(username);
        loginPage.enter_password(password);
    }

    @When("I click on login button")
    public void i_click_on_login_button(){
        loginPage.click_loginButton();
    }

    @Then("I should see logout link in burger menu")
    public void i_should_see_logout_link_in_burger_menu() {
        inventoryPage = pageObjectManager.getInventoryPage();
        inventoryPage.click_burgerMenu();
        assertTrue(inventoryPage.check_visibilityOfLogoutSideBar());
    }

    @Then("I should see error {string}")
    public void i_should_see_error_message(String message) {
        assertEquals(message, loginPage.get_loginErrorMessage());
    }
}
