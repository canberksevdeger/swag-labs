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
    private WebDriverManager driverManager;
    private PageObjectManager pageObjectManager;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;

    public CheckoutSteps(WebDriverManager driverManager) {
        this.driverManager = driverManager;
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
    }

    @When("I continue to checkout as {string} {string} {string}")
    public void i_continue_to_checkout(String firstName, String lastName, String postcode) {
        inventoryPage = pageObjectManager.getInventoryPage();
        inventoryPage.click_cartLink();
        checkoutPage = pageObjectManager.getCheckoutPage();
        checkoutPage.click_checkoutButton();
        checkoutPage.enter_firstName(firstName);
        checkoutPage.enter_lastName(lastName);
        checkoutPage.enter_postcode(postcode);
        checkoutPage.click_continueButton();
    }

    @When("I click on finish button")
    public void i_click_on_finish_button() {
        checkoutPage = pageObjectManager.getCheckoutPage();
        CucumberLogger.logger.info(checkoutPage.get_checkoutSummary());
        checkoutPage.click_finishButton();
    }

    @Then("I should see success order message")
    public void i_should_see_success_order_message() {
        checkoutPage = pageObjectManager.getCheckoutPage();
        assertTrue(checkoutPage.check_orderCompleteMessage());
    }
}
