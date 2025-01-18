package com.swag.labs.StepDefinition;

import com.swag.labs.utils.WebDriverManager;
import com.swag.labs.utils.constants.SortType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ParameterType;
import org.openqa.selenium.Cookie;
import com.swag.labs.pages.InventoryPage;
import com.swag.labs.utils.PageObjectManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventorySteps {
    private WebDriverManager driverManager;
    private PageObjectManager pageObjectManager;
    private InventoryPage inventoryPage;

    public InventorySteps(WebDriverManager driverManager) {
        this.driverManager = driverManager;
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
    }

    @Given("The user is on the inventory page")
    public void the_user_is_on_the_inventory_page() {
        driverManager.getDriver().get("https://www.saucedemo.com");
        driverManager.getDriver().manage().addCookie(new Cookie("session-username", "standard_user"));
        driverManager.getDriver().navigate().to("https://www.saucedemo.com/inventory.html");
    }

    @ParameterType("LOW_TO_HIGH|HIGH_TO_LOW|A_TO_Z|Z_TO_A")
    public SortType sortTypeOption(String option){
        return SortType.valueOf(option);
    }

    @When("Sort products as {sortTypeOption}")
    public void sort_products(SortType option){
        inventoryPage = pageObjectManager.getInventoryPage();
        inventoryPage.set_SortingOption(option);
    }

    @When("I add products to cart with {string} price")
    public void i_add_products_to_cart_with_price(String price) {
        inventoryPage = pageObjectManager.getInventoryPage();
        inventoryPage.click_addToCartButtons(
                inventoryPage.get_addToCartButtonsForSamePricedItems(price)
        );
    }

    @Then("Check product sorting type for {sortTypeOption}")
    public void check_product_sorting_type(SortType option) {
        inventoryPage = pageObjectManager.getInventoryPage();
        assertTrue(inventoryPage.check_PriceListSorted(inventoryPage.get_ItemsPriceList(), option));
    }
}
