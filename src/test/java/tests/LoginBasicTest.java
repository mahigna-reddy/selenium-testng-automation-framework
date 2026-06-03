package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.*;
import base.BaseTest;
import testdata.TestData;

// Test class for SauceDemo login, cart, checkout, and logout scenarios.

public class LoginBasicTest extends BaseTest {

	public void loginAsStandardUser() {
		driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys(TestData.STANDARD_USERNAME);
		driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys(TestData.PASSWORD);
		driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();
	}

	@Test
	public void verifyValidUserCanLoginSuccessfully() throws InterruptedException {
		System.out.println("0. Start");
		
		System.out.println("1. Open target page");
		driver.get(TestData.BASE_URL);
		Thread.sleep(2000);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("2. Login with valid user");
		loginAsStandardUser();
		Thread.sleep(2000);

		System.out.println("3. Verify login has been successfully executed");
		System.out.println(" 4.1 Page title is 'Swag Labs'");
		Assert.assertEquals(driver.getTitle(), "Swag Labs");
		
		System.out.println(" 3.2 Page url contains 'inventory'");
		Assert.assertTrue(
				driver.getCurrentUrl().contains("inventory"),
				"Inventory page was not opened"
		);
		//Pause the execution for 2 seconds to show the logged in page
		Thread.sleep(2000);
		System.out.println("4. End");
	}
	@Test
	public void verifyErrorMessageForInvalidLogin() throws InterruptedException {
		System.out.println("0. Start invalid login test");

		driver.get(TestData.BASE_URL);
		Thread.sleep(2000);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("1. Enter invalid username and password");
		driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys(TestData.INVALID_USERNAME);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys(TestData.INVALID_PASSWORD);
		System.out.println("2. Click login button");
		driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();
		Thread.sleep(2000);
		System.out.println("3. Verify error message is displayed");
		String errorMessage = driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText();
		Thread.sleep(2000);
		Assert.assertTrue(
				errorMessage.contains(TestData.LOGIN_ERROR_MESSAGE),
				"Error message was not displayed correctly"
		);

		System.out.println("4. End invalid login test");
	}

	@Test
	public void verifyUserCanAddProductToCart() throws InterruptedException {
		System.out.println("0. Start add product to cart test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));
		Thread.sleep(2000);

		System.out.println("1. Login with valid user");
		loginAsStandardUser();
		Thread.sleep(2000);

		System.out.println("2. Add Sauce Labs Backpack to cart");
		driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]")).click();
		Thread.sleep(1000);

		System.out.println("3. Verify cart badge shows 1 item");
		String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
		Assert.assertEquals(cartCount, "1");

		System.out.println("4. Click cart icon");
		driver.findElement(By.className("shopping_cart_link")).click();
		Thread.sleep(2000);

		System.out.println("5. Verify cart page is opened");
		Assert.assertTrue(
				driver.getCurrentUrl().contains("cart"),
				"Cart page was not opened"
		);

		System.out.println("6. Verify Sauce Labs Backpack is added to cart");
		String productName = driver.findElement(By.className("inventory_item_name")).getText();

		Assert.assertEquals(
				productName,
				TestData.BACKPACK_PRODUCT,
				"Sauce Labs Backpack was not added to the cart"
		);

		System.out.println("7. End add product to cart test");
	}

	@Test
	public void verifyUserCanCompleteCheckoutSuccessfully() throws InterruptedException {
		System.out.println("0. Start checkout test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));
		Thread.sleep(2000);

		System.out.println("1. Login with valid user");
		loginAsStandardUser();
		Thread.sleep(2000);

		System.out.println("2. Add multiple products to cart");
		driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]")).click();
		driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]")).click();
		driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();
		Thread.sleep(1000);


		System.out.println("3. Verify cart badge shows 3 items");
		String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
		Assert.assertEquals(cartCount, "3");

		System.out.println("4. Open cart");
		driver.findElement(By.className("shopping_cart_link")).click();
		Thread.sleep(2000);

		System.out.println("5. Verify all products are in cart");
		String cartPageText = driver.findElement(By.className("cart_list")).getText();

		Assert.assertTrue(cartPageText.contains(TestData.BACKPACK_PRODUCT), "Backpack is not displayed in cart");
		Assert.assertTrue(cartPageText.contains(TestData.BIKE_LIGHT_PRODUCT), "Bike Light is not displayed in cart");
		Assert.assertTrue(cartPageText.contains(TestData.BOLT_T_SHIRT_PRODUCT), "Bolt T-Shirt is not displayed in cart");
		System.out.println("6. Click checkout");
		driver.findElement(By.cssSelector("*[data-test=\"checkout\"]")).click();
		Thread.sleep(2000);

		System.out.println("7. Enter checkout information");
		driver.findElement(By.cssSelector("*[data-test=\"firstName\"]")).sendKeys(TestData.FIRST_NAME);
		driver.findElement(By.cssSelector("*[data-test=\"lastName\"]")).sendKeys(TestData.LAST_NAME);
		driver.findElement(By.cssSelector("*[data-test=\"postalCode\"]")).sendKeys(TestData.POSTAL_CODE);
		Thread.sleep(1000);

		System.out.println("8. Click continue");
		driver.findElement(By.cssSelector("*[data-test=\"continue\"]")).click();
		Thread.sleep(2000);

		System.out.println("9. Verify checkout overview page");
		Assert.assertTrue(
				driver.getCurrentUrl().contains("checkout-step-two"),
				"Checkout overview page is not opened"
		);

		System.out.println("10. Verify all products are displayed in checkout overview");
		String overviewPageText = driver.findElement(By.className("cart_list")).getText();

		Assert.assertTrue(overviewPageText.contains(TestData.BACKPACK_PRODUCT), "Backpack is not displayed in overview");
		Assert.assertTrue(overviewPageText.contains(TestData.BIKE_LIGHT_PRODUCT),  "Bike Light is not displayed in overview");
		Assert.assertTrue(overviewPageText.contains(TestData.BOLT_T_SHIRT_PRODUCT), "Bolt T-Shirt is not displayed in overview");

		System.out.println("11. Click finish");
		driver.findElement(By.cssSelector("*[data-test=\"finish\"]")).click();
		Thread.sleep(2000);


		System.out.println("12. Verify order confirmation message");
		String confirmationMessage = driver.findElement(By.className("complete-header")).getText();

		Assert.assertEquals(
				confirmationMessage,
				TestData.ORDER_CONFIRMATION_MESSAGE,
				"Order confirmation message is not displayed correctly"
		);

		System.out.println("13. Click back home");
		driver.findElement(By.cssSelector("*[data-test=\"back-to-products\"]")).click();
		Thread.sleep(2000);

		System.out.println("14. End checkout test");
		Thread.sleep(2000);
	}

	@Test
	public void verifyUserCanLogoutSuccessfully() throws InterruptedException {
		System.out.println("0. Start logout test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));
		Thread.sleep(2000);

		System.out.println("1. Login with valid user");
		loginAsStandardUser();
		Thread.sleep(2000);

		System.out.println("2. Open menu");
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(1000);

		System.out.println("3. Click logout");
		driver.findElement(By.id("logout_sidebar_link")).click();
		Thread.sleep(2000);

		System.out.println("4. Verify user is back on login page");
		Assert.assertTrue(
				driver.getCurrentUrl().contains(TestData.SAUCEDEMO_DOMAIN),
				"User was not navigated back to SauceDemo login page"
		);
		Assert.assertTrue(driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).isDisplayed());
		System.out.println("5. End logout test");
	}
}
