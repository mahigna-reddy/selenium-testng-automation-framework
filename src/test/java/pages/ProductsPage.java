package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private WebDriver driver;

    private By backpackAddToCartButton = By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]");
    private By bikeLightAddToCartButton = By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]");
    private By boltTShirtAddToCartButton = By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        driver.findElement(backpackAddToCartButton).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(bikeLightAddToCartButton).click();
    }

    public void addBoltTShirtToCart() {
        driver.findElement(boltTShirtAddToCartButton).click();
    }

    public void addMultipleProductsToCart() {
        addBackpackToCart();
        addBikeLightToCart();
        addBoltTShirtToCart();
    }

    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
}