package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By backpackAddToCartButton = By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]");
    private By bikeLightAddToCartButton = By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]");
    private By boltTShirtAddToCartButton = By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");
    private By backpackRemoveButton = By.cssSelector("*[data-test=\"remove-sauce-labs-backpack\"]");
    private By sortDropdown = By.className("product_sort_container");
    private By productPrices = By.className("inventory_item_price");
    private By backpackProductName = By.xpath("//div[text()='Sauce Labs Backpack']");
    private By backpackProductPrice = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
    private By productDetailName = By.className("inventory_details_name");
    private By productDetailPrice = By.className("inventory_details_price");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void sortByPriceLowToHigh() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (low to high)");
    }

    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }

        return prices;
    }

    public boolean arePricesSortedLowToHigh() {
        List<Double> prices = getProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    public String getBackpackProductNameFromProductsPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(backpackProductName)).getText();
    }

    public String getBackpackProductPriceFromProductsPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(backpackProductPrice)).getText();
    }

    public void openBackpackProductDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackProductName)).click();
    }

    public String getProductDetailName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailName)).getText();
    }

    public String getProductDetailPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailPrice)).getText();
    }


    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
    public void removeBackpackFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackRemoveButton)).click();
    }

    public boolean isCartBadgeDisplayed() {
        return driver.findElements(cartBadge).size() > 0;
    }

}