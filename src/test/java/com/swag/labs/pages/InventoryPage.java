package com.swag.labs.pages;

import java.util.*;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Ordering;
import com.swag.labs.utils.constants.SortType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage{
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using="inventory_container")
    private WebElement inventoryContainer;

    @FindBy(how = How.ID, using="inventory_list")
    private WebElement inventoryList;

    @FindAll(@FindBy(how = How.CLASS_NAME, using = "inventory_item"))
    private List<WebElement> inventoryItemList;

    @FindAll(@FindBy(how = How.CSS, using = "[data-test=inventory-item-price]"))
    private List<WebElement> inventoryItemPriceList;

    private By cartButton = By.className("btn_inventory");

    @FindBy(how = How.ID, using="react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(how = How.CSS, using = "[data-test=shopping-cart-link]")
    private WebElement cartLink;

    @FindBy(how = How.ID, using="logout_sidebar_link")
    private WebElement logoutSidebarLink;

    @FindBy(how = How.CSS, using = "[data-test=product-sort-container]")
    private WebElement productSortContainer;


    public void click_burgerMenu() {
        burgerMenu.click();
    }

    public Boolean check_visibilityOfLogoutSideBar() {
        return waitForVisible(logoutSidebarLink);
    }

    public void set_SortingOption(SortType option) {
        Select dropdown = new Select(productSortContainer);
        dropdown.selectByValue(option.text);
    }

    public List<WebElement> get_addToCartButtonsForSamePricedItems(String price) {
        List<WebElement> itemsWithSamePrice = new ArrayList<>();
        for (WebElement item: inventoryItemPriceList) {
            if (item.getText().equals(price)) {
                WebElement parentElement = get_ParentElement(item);
                WebElement addToCartButton = get_childElement(parentElement, cartButton);
                itemsWithSamePrice.add(addToCartButton);
            }
        }
        return itemsWithSamePrice;
    }

    public void click_addToCartButtons(List<WebElement> elementList) {
        for (WebElement element: elementList) {
            waitForClickableAndClick(element);
        }
    }

    public void click_cartLink() {
        waitForClickableAndClick(cartLink);
    }

    public List<Integer> get_ItemsPriceList() {
        List<Integer> priceList = new ArrayList<>();
        List<WebElement> itemPriceList = inventoryItemPriceList;
        for (WebElement inventoryItemPrice: itemPriceList){
            String priceText = getTextOfElement(inventoryItemPrice);
            String priceNumbers = CharMatcher.inRange('0', '9').retainFrom(priceText);
            int price = Integer.parseInt(priceNumbers);
            priceList.add(price);
        }
        return priceList;
    }

    public Boolean check_PriceListSorted(List<Integer> priceList, SortType option) {
        switch(option) {
            case HIGH_TO_LOW:
                return Ordering.natural().reverse().isOrdered(priceList);
            case LOW_TO_HIGH:
                return Ordering.natural().isOrdered(priceList);
            default:
                return false;
        }
    }
}
