# Selenium TestNG Automation Framework

This project is a Selenium TestNG automation framework built using Java, Maven, WebDriverManager, Page Object Model, TestNG, and explicit waits. It automates end-to-end test scenarios on the SauceDemo web application.

The goal of this project is to demonstrate how a basic Selenium automation script can be converted into a structured, reusable, and maintainable automation framework.

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

* **BaseTest**: Handles browser setup, ChromeOptions configuration, WebDriver initialization, explicit wait setup, and browser teardown.
* **Page Object Model**: Stores page-specific locators and actions in separate page classes.
* **TestData**: Maintains reusable test data such as URLs, credentials, product names, checkout information, cart counts, and expected messages.
* **Explicit Waits**: Uses `WebDriverWait` and `ExpectedConditions` to improve test stability.
* **TestNG**: Used for test execution and assertions.
* **Maven**: Used for dependency management and test execution.

## Automated Test Coverage

The framework covers key SauceDemo user flows including:

* Valid and invalid login validations
* Locked out user validation
* Product add-to-cart flow
* Product removal from cart page
* Cart persistence after continuing shopping
* Removing one item from a multi-product cart
* Multi-product checkout and order confirmation
* Checkout form validation
* Cancel checkout before placing order
* Product sorting validation
* Product details page validation
* Logout functionality

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

Handles login page actions such as entering username, entering password, clicking login, reading login error messages, and verifying login button visibility.

### ProductsPage

Handles product listing actions such as adding products to cart, removing products, sorting products, reading product prices, opening cart, and navigating to product details.

### CartPage

Handles cart page validations such as verifying products in cart, removing products from cart, clicking checkout, and continuing shopping.

### CheckoutPage

Handles checkout information entry, checkout overview validation, checkout error messages, finishing the order, canceling checkout, reading confirmation messages, and navigating back home.

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
* ChromeOptions configuration to handle browser password popups
* Assertions for login, negative login, cart, checkout, sorting, product details, and logout scenarios
* Demo mode support to slow down execution while observing test flow

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

* Building Selenium WebDriver tests using Java
* Structuring automation code using Page Object Model
* Creating reusable page classes
* Moving setup and teardown logic into `BaseTest`
* Managing reusable test data using constants
* Replacing hard waits with explicit waits
* Writing TestNG assertions for UI validations
* Automating positive and negative test scenarios
* Validating cart, checkout, sorting, product detail, and logout flows
* Committing and pushing automation code to GitHub

## Future Enhancements

Planned improvements for this framework:

* Split tests into separate classes such as `LoginTest`, `CartTest`, `CheckoutTest`, and `ProductTest`
* Add `config.properties` for environment-based configuration
* Add reusable utility methods for common browser actions
* Add Extent Reports or Allure Reports
* Add screenshots for failed test cases
* Add GitHub Actions for CI execution
* Add cross-browser execution support

## Author

Mahigna Reddy
