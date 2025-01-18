package com.swag.labs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class CheckoutPage extends BasePage{
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using="checkout")
    private WebElement checkoutButton;

    @FindBy(how = How.ID, using="first-name")
    private WebElement firstNameField;

    @FindBy(how = How.ID, using="last-name")
    private WebElement lastNameField;

    @FindBy(how = How.ID, using="postal-code")
    private WebElement postcodeField;

    @FindBy(how = How.ID, using = "continue")
    private WebElement continueButton;

    @FindBy(how = How.ID, using = "finish")
    private WebElement finishButton;

    @FindBy(how = How.CSS, using = "[data-test=complete-text]")
    private WebElement completeText;

    @FindAll(@FindBy(how = How.CSS, using = "[data-test=inventory-item]"))
    private List<WebElement> inventoryItems;

    private By inventoryItemName = By.className("inventory_item_name");
    private By inventoryItemPrice = By.className("inventory_item_price");

    @FindBy(how = How.CLASS_NAME, using = "summary_info")
    private WebElement summaryInfo;

    @FindBy(how = How.CSS, using = "[data-test=payment-info-value]")
    private WebElement paymentInfoValue;

    @FindBy(how = How.CSS, using = "[data-test=shipping-info-value]")
    private WebElement shippingInfoValue;

    @FindBy(how = How.CSS, using = "[data-test=subtotal-label]")
    private WebElement subtotalLabel;

    @FindBy(how = How.CSS, using = "[data-test=tax-label]")
    private WebElement taxLabel;

    @FindBy(how = How.CSS, using = "[data-test=total-label]")
    private WebElement totalLabel;

    public void click_checkoutButton() {
        waitForClickableAndClick(checkoutButton);
    }

    public void click_continueButton() {
        waitForClickableAndClick(continueButton);
    }

    public void click_finishButton() {
        waitForClickableAndClick(finishButton);
    }

    public void enter_firstName(String text) {
        firstNameField.sendKeys(text);
    }

    public void enter_lastName(String text) {
        lastNameField.sendKeys(text);
    }

    public void enter_postcode(String text) {
        postcodeField.sendKeys(text);
    }

    public Map<String, Object> get_checkoutSummary() {
        List<Map<String, Object>> products = new ArrayList<>();
        for(WebElement item: inventoryItems) {
            Map<String, Object> product = Map.of(
                    "product_name", get_childElement(item, inventoryItemName).getText(),
                    "product_price", get_childElement(item, inventoryItemPrice).getText()
            );
            products.add(product);
        }

        return Map.of(
                "payment_info", paymentInfoValue.getText(),
                "shipping_info", shippingInfoValue.getText(),
                "sub_total", subtotalLabel.getText(),
                "tax", taxLabel.getText(),
                "total", totalLabel.getText(),
                "products", products
        );
    }

    public Boolean check_orderCompleteMessage() {
        return waitForVisible(completeText);
    }
}
