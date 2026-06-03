# Selenium TestNG Automation Framework

This project is a Selenium TestNG automation framework built using Java, Maven, WebDriverManager, Page Object Model, and explicit waits. It automates end-to-end test scenarios on the SauceDemo web application.

## Application Under Test

SauceDemo: https://www.saucedemo.com/

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* IntelliJ IDEA
* GitHub

## Framework Design

This project follows a clean automation framework structure with reusable components:

* **BaseTest**: Handles browser setup, ChromeOptions configuration, WebDriver initialization, explicit wait setup, and teardown.
* **Page Object Model**: Stores page-specific locators and actions in separate page classes.
* **TestData**: Maintains reusable test data such as URLs, credentials, product names, checkout information, and expected messages.
* **Explicit Waits**: Uses `WebDriverWait` and `ExpectedConditions` instead of hard-coded `Thread.sleep()` waits.
* **TestNG**: Used for test execution and assertions.
* **Maven**: Used for dependency management and test execution.

## Automated Test Scenarios

The following test scenarios are automated:

1. Verify valid user can login successfully
2. Verify error message is displayed for invalid login
3. Verify user can add a product to the cart
4. Verify user can complete checkout successfully with multiple products
5. Verify user can logout successfully

## Project Structure

```text
selenium-testng-automation-framework
├── src
│   └── test
│       └── java
│           ├── base
│           │   └── BaseTest.java
│           ├── pages
│           │   ├── LoginPage.java
│           │   ├── ProductsPage.java
│           │   ├── CartPage.java
│           │   ├── CheckoutPage.java
│           │   └── MenuPage.java
│           ├── testdata
│           │   └── TestData.java
│           └── tests
│               └── LoginBasicTest.java
├── pom.xml
├── testng.xml
├── README.md
└── .gitignore
```

## Page Classes

### LoginPage

Handles login page actions such as entering username, entering password, clicking login, reading login error message, and verifying login button visibility.

### ProductsPage

Handles product listing actions such as adding products to cart, reading cart badge count, and opening the cart.

### CartPage

Handles cart page validations and checkout navigation.

### CheckoutPage

Handles checkout information entry, checkout overview validation, finishing the order, reading confirmation message, and navigating back home.

### MenuPage

Handles side menu actions such as opening the menu and logging out.

## Key Features

* Selenium WebDriver browser automation
* TestNG-based test execution
* Maven-based dependency management
* WebDriverManager for automatic ChromeDriver setup
* Page Object Model implementation
* Reusable `BaseTest` setup and teardown
* Reusable `TestData` constants
* Explicit waits using `WebDriverWait`
* ChromeOptions configuration to disable browser password manager popups during automation
* Assertions for login, error validation, cart count, cart contents, checkout overview, order confirmation, and logout

## How to Run Tests

Clone the repository:

```bash
git clone https://github.com/mahigna-reddy/selenium-testng-automation-framework.git
```

Go to the project folder:

```bash
cd selenium-testng-automation-framework
```

Run tests using Maven:

```bash
mvn clean test
```

## What I Practiced

Through this project, I practiced:

* Creating Selenium WebDriver tests from scratch
* Structuring tests using Page Object Model
* Creating reusable page classes
* Moving setup and teardown logic into `BaseTest`
* Managing reusable test data using constants
* Replacing hard waits with explicit waits
* Writing TestNG assertions for UI validation
* Running tests through IntelliJ and Maven
* Committing and pushing automation code to GitHub

## Future Enhancements

Planned improvements for this framework:

* Add `config.properties` for environment-based configuration
* Add reusable utility methods for common browser actions
* Add Extent Reports or Allure Reports
* Add screenshots for failed test cases
* Add separate test classes for Login, Cart, Checkout, and Logout flows
* Add GitHub Actions for CI execution
* Add cross-browser execution support

## Author

Mahigna Reddy
