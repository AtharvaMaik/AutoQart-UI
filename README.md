# AutoQart UI

`AutoQart UI` is a resume-ready UI test automation framework for [Automation Exercise](https://automationexercise.com), built with `Java`, `Selenium WebDriver`, `TestNG`, `Maven`, `Allure Reports`, and `GitHub Actions`.

It is designed to look and behave like a real beginner-friendly SDET project: reusable page objects, data-driven tests, cross-browser support, smoke and regression suites, reporting, and CI execution.

## Highlights
- Automated `61` UI test scenarios across authentication, navigation, products, cart, checkout, contact form, and subscription flows
- Supports `Chrome` and `Edge`
- Uses `Page Object Model (POM)` with reusable framework utilities
- Generates `Allure` results and screenshot evidence for failures
- Runs smoke tests on every push with `GitHub Actions`
- Includes `Maven Wrapper` so the project can run without a local Maven install

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

## What This Project Automates
- User signup, login, logout, invalid login, and duplicate-email validation
- Home page, header navigation, categories, brands, and recommended content checks
- Product search, product details, category filters, and brand filters
- Add-to-cart, remove-from-cart, multi-product cart, and cart persistence scenarios
- Guest checkout prompt and registered-user checkout entry flows
- Contact us form submission with file upload
- Footer email subscription validation

## Framework Design
- `src/main/java/com/automationexercise/pages`
  contains reusable page objects for each major flow
- `src/main/java/com/automationexercise/utils`
  includes config loading, waits, screenshots, JSON readers, random test data, and data providers
- `src/main/java/com/automationexercise/drivers`
  handles browser creation and runtime options
- `src/main/java/com/automationexercise/listeners`
  attaches test evidence to Allure on failures
- `src/test/java/com/automationexercise/tests`
  groups tests by business workflow instead of random page coverage
- `src/test/resources`
  stores config, suite XML files, logs, and JSON test data

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

## Run The Project

### Using Maven Wrapper
```powershell
.\mvnw.cmd clean test
.\mvnw.cmd "-DsuiteXmlFile=src/test/resources/smoke.xml" "-Dheadless=true" "-Dbrowser=chrome" test
.\mvnw.cmd "-DsuiteXmlFile=src/test/resources/regression.xml" "-Dheadless=true" "-Dbrowser=edge" test
```

### Using Maven
```bash
mvn clean test
mvn clean test -DsuiteXmlFile=src/test/resources/smoke.xml
mvn clean test -DsuiteXmlFile=src/test/resources/regression.xml
mvn clean test -Dbrowser=edge
```

## Reporting
- Raw Allure results are written to `allure-results/`
- Failure screenshots are written to `artifacts/screenshots/`
- GitHub Actions uploads report artifacts after execution

To open the report locally:
```bash
allure serve allure-results
```

## CI/CD
- Smoke suite runs on every push and pull request
- Regression suite runs on a nightly schedule
- Workflow uploads Allure results and screenshots as artifacts

## Demo Execution
A wrapper-based live demo run is documented in [docs/demo-run.md](docs/demo-run.md).

Verified command:
```powershell
.\mvnw.cmd "-DsuiteXmlFile=src/test/resources/smoke.xml" "-Dheadless=true" "-Dbrowser=chrome" test
```

Verified target website:
- `https://automationexercise.com`

## Resume Value
- Automated `50+` test cases with real browser execution
- Reduced the project to reusable, maintainable framework components
- Added CI-based smoke coverage for every code push
- Demonstrated reporting, test organization, and engineering discipline expected from an entry-level SDET

## Resume-Ready Description
Developed `AutoQart UI`, a scalable UI automation framework using `Java, Selenium WebDriver, TestNG, Maven, Allure Reports, and GitHub Actions` for an e-commerce practice application. Implemented `Page Object Model`, JSON-driven test data, reusable framework utilities, screenshot-based failure reporting, cross-browser execution, and CI-integrated smoke/regression suites. Automated `61` UI scenarios across authentication, product search, cart, checkout, contact form, and navigation workflows.
