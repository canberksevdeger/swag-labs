package com.swag.labs.utils;

import com.swag.labs.pages.CheckoutPage;
import org.openqa.selenium.WebDriver;
import com.swag.labs.pages.InventoryPage;
import com.swag.labs.pages.LoginPage;

public class PageObjectManager {
    private final WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public InventoryPage getInventoryPage() {
        return (inventoryPage == null) ? inventoryPage = new InventoryPage(driver) : inventoryPage;
    }

    public CheckoutPage getCheckoutPage() {
        return (checkoutPage == null) ? checkoutPage = new CheckoutPage(driver) : checkoutPage;
    }
}
