# AutoQart UI

AutoQart UI is a Selenium-based UI automation framework for [Automation Exercise](https://automationexercise.com). It is built with `Java`, `TestNG`, `Maven`, `Allure`, and `GitHub Actions`, and is organized around reusable page objects, workflow-based test suites, and CI-friendly execution.

## Overview
- Covers authentication, navigation, products, cart, checkout-entry, contact form, and subscription flows
- Supports `Chrome` and `Edge`
- Separates execution into `smoke` and `regression` suites
- Generates Allure results and screenshot evidence for failures
- Includes Maven Wrapper for consistent local and CI execution

## Tech Stack
- Java 17
- Selenium WebDriver 4
- TestNG
- Maven / Maven Wrapper
- WebDriverManager
- Allure Reports
- Jackson
- Log4j2
- GitHub Actions

## Test Coverage
- User signup, login, logout, invalid login, and duplicate-email handling
- Home page and header navigation validation
- Category and brand navigation
- Product search and product details flows
- Add-to-cart, remove-from-cart, multi-item cart, and cart persistence scenarios
- Guest checkout prompts and registered-user checkout entry flows
- Contact us form submission with file upload
- Footer subscription behavior

## Architecture
- `src/main/java/com/automationexercise/pages`
  Page objects for each major workflow
- `src/main/java/com/automationexercise/drivers`
  Browser creation and runtime configuration
- `src/main/java/com/automationexercise/utils`
  Config loading, waits, screenshots, JSON readers, random data, and data providers
- `src/main/java/com/automationexercise/listeners`
  TestNG listener integration for screenshots and Allure attachments
- `src/test/java/com/automationexercise/tests`
  Test classes grouped by business workflow
- `src/test/resources`
  Config, suite XML files, logging config, and JSON test data

## Project Structure
```text
AutoQart-UI
|-- .github/workflows/ui-automation.yml
|-- .mvn/wrapper
|-- mvnw
|-- mvnw.cmd
|-- pom.xml
|-- src
|   |-- main/java/com/automationexercise
|   |   |-- constants
|   |   |-- drivers
|   |   |-- factory
|   |   |-- listeners
|   |   |-- models
|   |   |-- pages
|   |   `-- utils
|   `-- test
|       |-- java/com/automationexercise/tests
|       `-- resources
|           |-- config.properties
|           |-- smoke.xml
|           |-- regression.xml
|           |-- testng.xml
|           `-- testdata
`-- docs
```

## Prerequisites
- Java 17 or newer
- Chrome or Edge installed locally
- Optional: Allure CLI if you want to render reports locally

## Configuration
Runtime settings live in `src/test/resources/config.properties`.

Available settings include:
- `base.url`
- `browser`
- `headless`
- `implicit.wait`
- `explicit.wait`
- `page.load.timeout`
- `script.timeout`

These can also be overridden from the command line with system properties such as `-Dbrowser=edge` or `-Dheadless=true`.

## Running The Tests

### With Maven Wrapper
```powershell
.\mvnw.cmd clean test
.\mvnw.cmd "-DsuiteXmlFile=src/test/resources/smoke.xml" "-Dheadless=true" "-Dbrowser=chrome" test
.\mvnw.cmd "-DsuiteXmlFile=src/test/resources/regression.xml" "-Dheadless=true" "-Dbrowser=edge" test
```

### With Maven
```bash
mvn clean test
mvn clean test -DsuiteXmlFile=src/test/resources/smoke.xml
mvn clean test -DsuiteXmlFile=src/test/resources/regression.xml
mvn clean test -Dbrowser=edge
```

## Reporting
- Allure raw results are written to `allure-results/`
- Failure screenshots are written to `artifacts/screenshots/`
- The GitHub Actions workflow uploads these artifacts after each run

To open the report locally:
```bash
allure serve allure-results
```

## CI/CD
The repository includes a GitHub Actions workflow at `.github/workflows/ui-automation.yml`.

It currently:
- runs the smoke suite on push and pull request
- runs the regression suite on a nightly schedule
- uploads Allure results and screenshots as artifacts

## Demo Execution
A wrapper-based live execution example is documented in [docs/demo-run.md](docs/demo-run.md).

Verified command:
```powershell
.\mvnw.cmd "-DsuiteXmlFile=src/test/resources/smoke.xml" "-Dheadless=true" "-Dbrowser=chrome" test
```

Verified target website:
- `https://automationexercise.com`

## Notes
- Chrome may emit CDP compatibility warnings depending on the locally installed browser version. In current verification runs, those warnings did not block execution.
- SLF4J may emit a missing binder warning at runtime. It does not affect compilation or test execution in the current setup.
