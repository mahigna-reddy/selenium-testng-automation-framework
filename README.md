# Selenium TestNG Automation Framework

This project is a Selenium automation framework built using Java, TestNG, Maven, and WebDriverManager. It automates end-to-end test scenarios on the SauceDemo web application.

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* IntelliJ IDEA
* GitHub

## Application Under Test

SauceDemo: https://www.saucedemo.com/

## Automated Test Scenarios

The following test cases are automated:

1. Verify valid user can login successfully
2. Verify error message for invalid login
3. Verify user can add product to cart
4. Verify user can logout successfully
5. Verify user can complete checkout successfully with multiple products

## Key Features

* Browser automation using Selenium WebDriver
* Test execution using TestNG
* Maven-based dependency management
* WebDriverManager for automatic browser driver setup
* ChromeOptions configuration to disable browser password popups during automation
* Reusable login helper method for standard user login
* Assertions for validating login, error messages, cart count, cart contents, checkout overview, logout, and order confirmation
* Clean and readable test steps with console logs

## Project Structure

```text
selenium-testng-automation-framework
├── src
│   └── test
│       └── java
│           └── tests
│               └── LoginBasicTest.java
├── pom.xml
├── testng.xml
├── README.md
└── .gitignore
```

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

* Creating and running Selenium WebDriver tests
* Using TestNG annotations such as `@Test`, `@BeforeMethod`, and `@AfterMethod`
* Managing dependencies using Maven
* Using WebDriverManager instead of manually downloading browser drivers
* Writing assertions to validate expected results
* Automating complete end-to-end user flows
* Using GitHub to maintain and share automation code

## Author

Mahigna Reddy
