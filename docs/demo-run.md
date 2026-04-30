# Demo Run: Maven Wrapper Execution

## Website
- Target: `https://automationexercise.com`
- Browser: `Chrome`
- Mode: `headless`
- Suite: `smoke`

## Command Used
```powershell
.\mvnw.cmd "-DsuiteXmlFile=src/test/resources/smoke.xml" "-Dheadless=true" "-Dbrowser=chrome" test
```

## What This Demo Covered
- New user registration
- Login with created user
- Product search
- Add to cart
- Remove from cart
- Contact form submission
- Footer subscription validation

## Observed Result
- Command completed successfully with exit code `0`
- Smoke flow ran successfully against the live AutomationExercise website
- Allure raw results were generated in `allure-results/`
- Failure screenshots remain configured in `artifacts/screenshots/` for any future failing runs

## Notes
- Runtime emitted Chrome CDP compatibility warnings for the locally installed Chrome `147` build, but the suite still executed successfully
- SLF4J emitted a missing binder warning at runtime, but it did not block compilation or test execution
