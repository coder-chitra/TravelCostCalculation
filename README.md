# TravelCostCalculation

Automated end-to-end testing flows for [Booking.com](https://www.booking.com) using Selenium and Java.  
This project validates multiple booking scenarios with dynamic inputs, reporting, and screenshots.

---

## Features

- **Cruise Testcase**  
  - Location: London  
  - Dynamic date selection  
  - Applied cruise filter  
  - Extracted details of top 3 cruises  

- **Hotels Testcase**  
  - Location: Nairobi  
  - Dynamic date selection  
  - Filtered by WiFi option  
  - Retrieved all hotel details  

- **Negative Testcase**  
  - Validated error message for invalid email input  

- **Reporting & Execution**  
  - Integrated **Allure Reports** for detailed test results  
  - Multi-browser support (Chrome, Firefox, etc.)  
  - Screenshot capture after every testcase  

---

## Technologies Used

- **Selenium WebDriver** – Browser automation  
- **Java** – Core programming language  
- **TestNG** – Test framework  
- **Maven** – Build and dependency management  
- **Allure Reports** – Test reporting  

---

## Project Structure

```
TravelCostCalculation/
├── src/test/java/        # Test scripts
├── pom.xml               # Maven dependencies
├── allure-results/       # Allure report files
└── README.md             # Project documentation
```

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/coder-chitra/TravelCostCalculation.git
   ```
2. Navigate to the project folder:
   ```bash
   cd TravelCostCalculation
   ```
3. Run tests with Maven:
   ```bash
   mvn clean test
   ```
4. Generate and open Allure report:
   ```bash
   mvn allure:serve
   ```

---

## Screenshots

- Screenshots are automatically captured after each testcase execution.  
- Available in the `screenshots/` folder and embedded in Allure reports.

---

## Reports

- Allure provides interactive HTML reports with:
  - Test execution timeline  
  - Step-by-step logs  
  - Screenshots for each testcase  

---
