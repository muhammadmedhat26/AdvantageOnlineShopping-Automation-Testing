# Test Plan — Advantage Online Shopping Automation Testing

Project: AdvantageOnlineShopping-Automation-Testing

Repository: github.com/muhammadmedhat26/AdvantageOnlineShopping-Automation-Testing

# 1. Project Introduction

## 1.1 Testing Website/Target

Advantage Online Shopping — https://advantageonlineshopping.com/#/ (live public demo e-commerce application).

## 1.2 Summary

This project automates UI testing of the Advantage Online Shopping web application across its core user-facing modules: Registration, Login, Homepage navigation, Product browsing, Shopping Cart, and Checkout. The suite was originally built as part of a Konecta internship graduation project, then rebuilt this cycle using Selenium WebDriver (Java), TestNG, and the Page Object Model, with a manual test case sheet (Google Sheets) kept as the design reference for which cases were selected for automation.

## 1.3 Purpose

To verify that the application's core user flows behave correctly end to end, and to document and assert against the application's actual validation and UI behavior — including several cases where the live application's behavior differs from the expected behavior defined in the test case sheet (e.g. accepted invalid input, broken links, non-functional features).

# 2. Scope

## 2.1 In Scope (Modules)

- Registration — valid registration, blank mandatory fields, invalid email format, existing username, "already have an account" redirect
- Login — valid login, invalid credentials, forgot password behavior, navigation to registration
- Homepage — category navigation, header icon functionality (search/profile/cart), popular item links
- Product — quantity selector (increase/decrease/boundary/non-numeric input), add to cart
- Shopping Cart — add/edit/remove items, quantity persistence, cart counter, large-quantity additions
- Checkout — shipping details, SafePay payment, MasterCard payment, order confirmation
- End-to-End journey — registration through checkout in a single continuous flow

## 2.2 Out of Scope

- Performance, load, or stress testing
- Mobile testing
- API testing
- Cross-browser compatibility testing (Chrome only)
- Visual/layout-only checks not tied to a functional assertion (e.g. spacing, font rendering)
- Third-party payment gateway security testing
- Out-of-stock product behavior — not currently testable; the live site carries no out-of-stock items

# 3. Test Objectives

- Confirm each in-scope module's primary user flows complete successfully under valid input.
- Confirm the application rejects or correctly handles invalid input (blank fields, bad email format, non-numeric quantity, existing username), and document cases where it does not.
- Confirm cart and checkout state (item count, quantity, total) remains consistent across navigation and actions.
- Surface and document known application defects as regression-proof tests, so future runs flag if the defect is fixed or a passing flow regresses.
- Validate one complete, realistic user journey from registration through order confirmation.

# 4. Test Environment

| Component | Detail |
| --- | --- |
| Target | https://advantageonlineshopping.com/#/ |
| Language | Java 17 |
| Build tool | Maven |
| Test framework | TestNG |
| Automation | Selenium WebDriver (DriverFactory supports Chrome, Firefox, Edge via direct driver instantiation) |
| Design pattern | Page Object Model (POM) |
| IDE | IntelliJ IDEA |
| Browser | Google Chrome (default; Firefox and Edge also supported via DriverFactory) |
| OS | Windows 10 |
| Manual test design | Google Sheets test case inventory (Login, Registration, Homepage, Product Details, Cart, Checkout, Account/Orders tabs) |
| Execution config | src/test/resources/testng.xml — fixed test-class order, no parallelism |

# 5. Entry & Exit Criteria

## 5.1 Entry Criteria

- Target browser (Chrome by default) installed locally
- `mvn clean install` completes successfully
- Test data (`TestData.java`) holds a valid, currently-registered username/password for login-dependent tests

## 5.2 Exit Criteria

- All test classes in `testng.xml` execute without unexpected build failure
- Any new test case added to the code is reflected in the test case sheet

# 6. Test Approach

Tests run via `testng.xml` in the following logical order, grouped so later modules can assume earlier ones are already verified:

- **LoginTest** — valid/invalid login, forgot password, navigation to registration
- **RegistrationTest** — valid registration (with cleanup via account deletion), blank fields, bad email, existing username, sign-in redirect
- **HomeTest** — category navigation, header icon functionality, popular item link correctness
- **ProductTest** — quantity selector, quantity boundaries, non-numeric input, add to cart
- **CartTest** — open/edit/remove cart items, quantity persistence, cart counter, large-quantity additions, navigation to checkout
- **CheckoutTest** — shipping details, SafePay and MasterCard payment, order confirmation
- **EndToEndJourneyTest** — registration → browsing → cart → checkout → account deletion, run last, after each individual module is confirmed working in isolation

`BaseTest` uses TestNG groups (`requiresLogin`, `checkoutTests`) to conditionally run shared setup — login before any test tagged `requiresLogin`, and navigation to checkout before any test tagged `checkoutTests` — so individual test classes do not repeat setup steps inline.

## 6.1 Testing Types & Technique

| Type | Technique |
| --- | --- |
| Functional / Positive | Happy-path flows per module, asserting the UI reflects the action taken (e.g. cart count, quantity field, confirmation message) |
| Negative — Validation | Equivalence partitioning on blank/invalid required fields (registration, login) |
| Negative — Boundary | Quantity field boundaries (0, large values) and non-numeric input on the product page |
| Regression / Bug-proof | Tests that assert the application's actual (defective) behavior — e.g. forgot password, broken popular-item link — so a future fix or regression is caught either way |
| End-to-End | Single continuous journey across all modules, registration through order confirmation |

# 7. Deliverables, Tools, and Roles

## Deliverables

- Test plan (this document)
- Automated test suite (`src/test/java`)
- Source code (GitHub repository)
- Recorded demo of the suite running
- Presentation

## Tools

Maven, TestNG, Selenium WebDriver, IntelliJ IDEA, Google Sheets (manual test case design)

## Roles

**Muhammad Medhat** — Manual Testing, Test Design, Automation Framework Architecture; Login, Registration, Product, Cart, Homepage, and End-to-End journey automation

**Salma Mizar** — Manual Testing, Test Design, Automation Framework Architecture; Login, Registration, Product, Cart, and Checkout automation
