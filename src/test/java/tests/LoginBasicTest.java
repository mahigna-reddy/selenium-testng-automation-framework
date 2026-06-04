package tests;

import base.BaseTest;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.MenuPage;
import testdata.TestData;

// Test class for SauceDemo login, cart, checkout, and logout scenarios.

public class LoginBasicTest extends BaseTest {

	public void loginAsStandardUser() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(TestData.STANDARD_USERNAME, TestData.PASSWORD);
	}

	@Test
	public void verifyValidUserCanLoginSuccessfully() {
		System.out.println("0. Start");

		System.out.println("1. Open target page");
		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("2. Login with valid user");
		loginAsStandardUser();

		System.out.println("3. Verify login has been successfully executed");
		System.out.println(" 4.1 Page title is 'Swag Labs'");
		Assert.assertEquals(driver.getTitle(), "Swag Labs");

		System.out.println(" 3.2 Page url contains 'inventory'");
		Assert.assertTrue(
				waitForUrlToContain(TestData.INVENTORY_PAGE_URL_KEYWORD),
				"Inventory page was not opened"
		);
		System.out.println("4. End");
	}

	@Test
	public void verifyErrorMessageForInvalidLogin() throws InterruptedException {
		System.out.println("0. Start invalid login test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("1. Enter invalid username and password");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(TestData.INVALID_USERNAME);
		loginPage.enterPassword(TestData.INVALID_PASSWORD);
		System.out.println("2. Click login button");
		loginPage.clickLoginButton();
		System.out.println("3. Verify error message is displayed");
		String errorMessage = loginPage.getErrorMessage();
		Assert.assertTrue(
				errorMessage.contains(TestData.LOGIN_ERROR_MESSAGE),
				"Error message was not displayed correctly");
		System.out.println("4. End invalid login test");
	}

	@Test
	public void verifyUserCanAddProductToCart() throws InterruptedException {
		System.out.println("0. Start add product to cart test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("1. Login with valid user");
		loginAsStandardUser();

		ProductsPage productsPage = new ProductsPage(driver);
		CartPage cartPage = new CartPage(driver);

		System.out.println("2. Add Sauce Labs Backpack to cart");
		productsPage.addBackpackToCart();

		System.out.println("3. Verify cart badge shows 1 item");
		String cartCount = productsPage.getCartBadgeCount();
		Assert.assertEquals(cartCount, TestData.ONE_ITEM_CART_COUNT);

		System.out.println("4. Click cart icon");
		productsPage.openCart();

		System.out.println("5. Verify cart page is opened");
		Assert.assertTrue(
				driver.getCurrentUrl().contains(TestData.CART_PAGE_URL_KEYWORD),
				"Cart page was not opened"
		);

		System.out.println("6. Verify Sauce Labs Backpack is added to cart");
		String productName = cartPage.getProductName();

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

		System.out.println("1. Login with valid user");
		loginAsStandardUser();

		ProductsPage productsPage = new ProductsPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);

		System.out.println("2. Add multiple products to cart");
		productsPage.addMultipleProductsToCart();
		Thread.sleep(1000);

		System.out.println("3. Verify cart badge shows 3 items");
		String cartCount = productsPage.getCartBadgeCount();
		Assert.assertEquals(cartCount, TestData.THREE_ITEMS_CART_COUNT);

		System.out.println("4. Open cart");
		productsPage.openCart();

		System.out.println("5. Verify all products are in cart");
		String cartPageText = cartPage.getCartPageText();

		Assert.assertTrue(cartPageText.contains(TestData.BACKPACK_PRODUCT), "Backpack is not displayed in cart");
		Assert.assertTrue(cartPageText.contains(TestData.BIKE_LIGHT_PRODUCT), "Bike Light is not displayed in cart");
		Assert.assertTrue(cartPageText.contains(TestData.BOLT_T_SHIRT_PRODUCT), "Bolt T-Shirt is not displayed in cart");

		System.out.println("6. Click checkout");
		cartPage.clickCheckout();

		System.out.println("7. Enter checkout information");
		checkoutPage.enterCheckoutInformation(
				TestData.FIRST_NAME,
				TestData.LAST_NAME,
				TestData.POSTAL_CODE
		);
		Thread.sleep(1000);

		System.out.println("8. Click continue");
		checkoutPage.clickContinue();

		System.out.println("9. Verify checkout overview page");
		Assert.assertTrue(
				driver.getCurrentUrl().contains(TestData.CHECKOUT_OVERVIEW_URL_KEYWORD),
				"Checkout overview page is not opened"
		);

		System.out.println("10. Verify all products are displayed in checkout overview");
		String overviewPageText = checkoutPage.getOverviewPageText();

		Assert.assertTrue(overviewPageText.contains(TestData.BACKPACK_PRODUCT), "Backpack is not displayed in overview");
		Assert.assertTrue(overviewPageText.contains(TestData.BIKE_LIGHT_PRODUCT), "Bike Light is not displayed in overview");
		Assert.assertTrue(overviewPageText.contains(TestData.BOLT_T_SHIRT_PRODUCT), "Bolt T-Shirt is not displayed in overview");

		System.out.println("11. Click finish");
		checkoutPage.clickFinish();

		System.out.println("12. Verify order confirmation message");
		String confirmationMessage = checkoutPage.getConfirmationMessage();

		Assert.assertEquals(
				confirmationMessage,
				TestData.ORDER_CONFIRMATION_MESSAGE,
				"Order confirmation message is not displayed correctly"
		);

		System.out.println("13. Click back home");
		checkoutPage.clickBackHome();

		System.out.println("14. End checkout test");
	}

	@Test
	public void verifyUserCanLogoutSuccessfully() {
		System.out.println("0. Start logout test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("1. Login with valid user");
		loginAsStandardUser();

		System.out.println("2. Open menu");
		MenuPage menuPage = new MenuPage(driver);
		menuPage.openMenu();

		System.out.println("3. Click logout");
		menuPage.clickLogout();

		System.out.println("4. Verify user is back on login page");
		Assert.assertTrue(
				waitForUrlToContain(TestData.SAUCEDEMO_DOMAIN),
				"User was not navigated back to SauceDemo login page"
		);

		LoginPage loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.isLoginButtonDisplayed());

		System.out.println("5. End logout test");
	}

	@Test
	public void verifyUserCanRemoveProductFromCartPage() {
		System.out.println("0. Start remove product from cart page test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("1. Login with valid user");
		loginAsStandardUser();
		slowDownForDemo();

		ProductsPage productsPage = new ProductsPage(driver);
		CartPage cartPage = new CartPage(driver);

		System.out.println("2. Add Sauce Labs Backpack to cart");
		productsPage.addBackpackToCart();
		slowDownForDemo();

		System.out.println("3. Verify cart badge shows 1 item");
		String cartCount = productsPage.getCartBadgeCount();

		Assert.assertEquals(
				cartCount,
				TestData.ONE_ITEM_CART_COUNT,
				"Cart badge count is not showing 1 after adding product"
		);

		System.out.println("4. Click cart icon");
		productsPage.openCart();
		slowDownForDemo();

		System.out.println("5. Verify cart page is opened");
		Assert.assertTrue(
				waitForUrlToContain(TestData.CART_PAGE_URL_KEYWORD),
				"Cart page was not opened"
		);

		System.out.println("6. Verify Sauce Labs Backpack is displayed in cart");
		Assert.assertTrue(
				cartPage.isProductDisplayedInCart(TestData.BACKPACK_PRODUCT),
				"Sauce Labs Backpack is not displayed in cart"
		);

		System.out.println("7. Remove Sauce Labs Backpack from cart page");
		cartPage.removeBackpackFromCart();
		slowDownForDemo();

		System.out.println("8. Verify cart badge is removed");
		Assert.assertFalse(
				cartPage.isCartBadgeDisplayed(),
				"Cart badge is still displayed after removing the product from cart page"
		);

		System.out.println("9. End remove product from cart page test");
	}

	@Test
	public void verifyCheckoutErrorWhenFirstNameIsNotEntered() {
		System.out.println("0. Start checkout missing first name test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("1. Login with valid user");
		loginAsStandardUser();
		slowDownForDemo();

		ProductsPage productsPage = new ProductsPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);

		System.out.println("2. Add Sauce Labs Backpack to cart");
		productsPage.addBackpackToCart();
		slowDownForDemo();

		System.out.println("3. Open cart");
		productsPage.openCart();

		System.out.println("4. Click checkout");
		cartPage.clickCheckout();

		System.out.println("5. Enter checkout information without first name");
		checkoutPage.enterCheckoutInformation(
				"",
				TestData.LAST_NAME,
				TestData.POSTAL_CODE
		);

		System.out.println("6. Click continue");
		checkoutPage.clickContinue();
		slowDownForDemo();

		System.out.println("7. Verify first name required error message");
		String errorMessage = checkoutPage.getCheckoutErrorMessage();

		Assert.assertEquals(
				errorMessage,
				TestData.FIRST_NAME_REQUIRED_ERROR,
				"First name required error message was not displayed correctly"
		);

		System.out.println("8. End checkout missing first name test");

	}
	@Test
	public void verifyLockedOutUserCannotLogin() {
		System.out.println("0. Start locked out user login test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		LoginPage loginPage = new LoginPage(driver);

		System.out.println("1. Login with locked out user");
		loginPage.login(TestData.LOCKED_OUT_USERNAME, TestData.PASSWORD);
		slowDownForDemo();

		System.out.println("2. Verify locked out error message");
		String errorMessage = loginPage.getErrorMessage();

		Assert.assertTrue(
				errorMessage.contains(TestData.LOCKED_OUT_ERROR_MESSAGE),
				"Locked out user error message was not displayed correctly"
		);

		System.out.println("3. End locked out user login test");
	}

	@Test
	public void verifyProductsAreSortedByPriceLowToHigh() {
		System.out.println("0. Start product price sorting test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("1. Login with valid user");
		loginAsStandardUser();
		slowDownForDemo();

		ProductsPage productsPage = new ProductsPage(driver);

		System.out.println("2. Sort products by price low to high");
		productsPage.sortByPriceLowToHigh();
		slowDownForDemo();

		System.out.println("3. Verify products are sorted by price low to high");
		Assert.assertTrue(
				productsPage.arePricesSortedLowToHigh(),
				"Products are not sorted by price low to high"
		);

		System.out.println("4. End product price sorting test");
	}

	@Test
	public void verifyBackpackProductDetailsPage() {
		System.out.println("0. Start product details page test");

		driver.get(TestData.BASE_URL);
		driver.manage().window().setSize(new Dimension(1350, 637));

		System.out.println("1. Login with valid user");
		loginAsStandardUser();
		slowDownForDemo();

		ProductsPage productsPage = new ProductsPage(driver);

		System.out.println("2. Capture product name and price from products page");
		String expectedProductName = productsPage.getBackpackProductNameFromProductsPage();
		String expectedProductPrice = productsPage.getBackpackProductPriceFromProductsPage();

		System.out.println("3. Open Sauce Labs Backpack product details page");
		productsPage.openBackpackProductDetails();
		slowDownForDemo();

		System.out.println("4. Verify product name on details page");
		String actualProductName = productsPage.getProductDetailName();

		Assert.assertEquals(
				actualProductName,
				expectedProductName,
				"Product name on details page does not match products page"
		);

		System.out.println("5. Verify product price on details page");
		String actualProductPrice = productsPage.getProductDetailPrice();

		Assert.assertEquals(
				actualProductPrice,
				expectedProductPrice,
				"Product price on details page does not match products page"
		);

		System.out.println("6. End product details page test");
	}

}