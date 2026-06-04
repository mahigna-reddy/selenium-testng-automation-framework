package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartList = By.className("cart_list");
    private By productName = By.className("inventory_item_name");
    private By checkoutButton = By.cssSelector("*[data-test=\"checkout\"]");
    private By backpackRemoveButton = By.cssSelector("*[data-test=\"remove-sauce-labs-backpack\"]");
    private By cartBadge = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void removeBackpackFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackRemoveButton)).click();
    }

    public boolean isCartBadgeDisplayed() {
        return driver.findElements(cartBadge).size() > 0;
    }

    public boolean isProductDisplayedInCart(String productName) {
        return getCartPageText().contains(productName);
    }
}