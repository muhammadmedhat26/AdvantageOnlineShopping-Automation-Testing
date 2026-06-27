# Advantage Online Shopping — Automation Testing

Selenium WebDriver (Java) automation suite for [Advantage Online Shopping](https://advantageonlineshopping.com/#/), a live public demo e-commerce site. Built using the Page Object Model with TestNG.

See [TestPlan.md](TestPlan.md) for full scope, test approach, and entry/exit criteria.

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver 4.44.0
- TestNG 7.12.0

## Project Structure

```
src/main/java/
├── TestData/         # Test data constants (credentials, product names, payment info)
├── factory/          # DriverFactory — Chrome/Firefox/Edge driver setup
└── pages/            # Page Object Model classes
    ├── BasePage
    ├── HomePage
    ├── LoginPage
    ├── RegistrationPage
    ├── ProductPage
    ├── CartPage
    └── CheckoutPage

src/test/java/
├── setup/
│   └── BaseTest      # Shared setup via TestNG groups (requiresLogin, checkoutTests)
└── testcases/
    ├── LoginTest
    ├── RegistrationTest
    ├── HomeTest
    ├── ProductTest
    ├── CartTest
    ├── CheckoutTest
    └── EndToEndJourneyTest

src/test/resources/
└── testng.xml         # Suite execution order
```

## Modules Covered

| Module | Coverage |
| --- | --- |
| Login | Valid/invalid login, forgot password, navigation to registration |
| Registration | Valid registration (with account cleanup), blank fields, bad email, existing username, sign-in redirect |
| Homepage | Category navigation, header icons, popular item links |
| Product | Quantity selector, quantity boundaries, non-numeric input, add to cart |
| Cart | Add/edit/remove items, quantity persistence, cart counter, large-quantity additions |
| Checkout | Shipping details, SafePay and MasterCard payment, order confirmation |
| End-to-End | Full journey: registration → browsing → cart → checkout → account deletion |

## Setup

1. Clone the repo
2. Have Chrome installed (default browser in `DriverFactory`; Firefox and Edge are also supported)
3. Set a valid, currently-registered username/password in `TestData.java` for login-dependent tests

## Running the Tests

```
mvn clean install
mvn test
```

Tests run via `src/test/resources/testng.xml`. To run a single class:

```
mvn test -Dtest=LoginTest
```

To run on a different browser:

```
mvn test -Dbrowser=FIREFOX
```

## Manual Test Design

A Google Sheets test case inventory (Login, Registration, Homepage, Product Details, Cart, Checkout, Account/Orders) was used as the design reference for selecting which cases to automate.

## Contributors

- **Muhammad Medhat**
- **Salma Mizar** 
