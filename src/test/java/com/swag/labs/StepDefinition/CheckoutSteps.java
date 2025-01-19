package com.swag.labs.StepDefinition;

import com.swag.labs.pages.CheckoutPage;
import com.swag.labs.utils.CucumberLogger;
import com.swag.labs.utils.WebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.swag.labs.pages.InventoryPage;
import com.swag.labs.utils.PageObjectManager;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class CheckoutSteps {
    private PageObjectManager pageObjectManager;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;

    public CheckoutSteps(WebDriverManager driverManager) {
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
    }

    @When("I continue to checkout as {string} {string} {string}")
    public void i_continue_to_checkout(String firstName, String lastName, String postcode) {
        inventoryPage = pageObjectManager.getInventoryPage();
        inventoryPage.clickCartLink();
        checkoutPage = pageObjectManager.getCheckoutPage();
        checkoutPage.clickCheckoutButton();
        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterLastName(lastName);
        checkoutPage.enterPostcode(postcode);
        checkoutPage.clickContinueButton();
    }

    @When("I click on finish button")
    public void i_click_on_finish_button() {
        checkoutPage = pageObjectManager.getCheckoutPage();
        CucumberLogger.logger.info(checkoutPage.getCheckoutSummary());
        checkoutPage.clickFinishButton();
    }

    @Then("I should see success order message")
    public void i_should_see_success_order_message() {
        checkoutPage = pageObjectManager.getCheckoutPage();
        assertTrue(checkoutPage.checkOrderCompleteMessage());
    }
}
