package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    private By cartList = By.className("cart_list");
    private By productName = By.className("inventory_item_name");
    private By checkoutButton = By.cssSelector("*[data-test=\"checkout\"]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartPageText() {
        return driver.findElement(cartList).getText();
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}