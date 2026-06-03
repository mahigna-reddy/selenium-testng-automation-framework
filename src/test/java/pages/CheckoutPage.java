package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    private By firstNameField = By.cssSelector("*[data-test=\"firstName\"]");
    private By lastNameField = By.cssSelector("*[data-test=\"lastName\"]");
    private By postalCodeField = By.cssSelector("*[data-test=\"postalCode\"]");
    private By continueButton = By.cssSelector("*[data-test=\"continue\"]");
    private By overviewCartList = By.className("cart_list");
    private By finishButton = By.cssSelector("*[data-test=\"finish\"]");
    private By confirmationMessage = By.className("complete-header");
    private By backHomeButton = By.cssSelector("*[data-test=\"back-to-products\"]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public String getOverviewPageText() {
        return driver.findElement(overviewCartList).getText();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }

    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }
}