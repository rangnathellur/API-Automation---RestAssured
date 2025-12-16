# Quality Engineering – API Automation using RestAssured

This repository contains a scalable **API automation platform** built using RestAssured and TestNG.  
The platform demonstrates how an Automation Lead or Manager would design, standardize, and govern API test automation across teams in an enterprise environment.

The framework is intentionally application-agnostic and focuses on clean architecture, data-driven testing, and CI/CD readiness rather than tool-specific scripting.

---

## Objectives

- Establish a standardized API automation foundation
- Enable reliable CRUD API validation (GET, POST, PATCH, DELETE)
- Support parallel execution and CI/CD integration
- Promote maintainable, reusable, and readable test design
- Demonstrate ownership of an enterprise-quality API testing platform

---

## Technology Stack

- Java 11+
- RestAssured
- TestNG
- Maven
- JSON (org.json)
- Allure Reporting (optional)

---

## Platform Architecture

The platform is structured to clearly separate framework concerns from test logic.

quality-engineering-api-platform/
├─ pom.xml
├─ testng.xml
├─ src/
│ ├─ main/java/com/ranganath/qe/framework/
│ │ ├─ config/ → Centralized configuration management
│ │ ├─ utils/ → JSON parsing, data providers, helpers
│ │ └─ listeners/ → Test execution hooks and reporting
│ └─ test/java/com/ranganath/qe/tests/
│ ├─ base/ → Suite-level RestAssured configuration
│ ├─ endpoints/ → API endpoint abstraction layer
│ └─ tests/ → Data-driven API test suites
├─ src/test/resources/
│ ├─ config.properties → Environment configuration
│ └─ testdata.json → Externalized request payloads
└─ target/ → Execution artifacts and reports


---

## Design Principles

- **Endpoint abstraction** similar to Page Object Model for APIs
- **Suite-level configuration** to ensure stability across parallel runs
- **Externalized configuration** for environment flexibility
- **Data-driven testing** using JSON payloads
- **Fail-fast validation** for misconfiguration
- **Thread-safe execution** suitable for CI/CD pipelines

---

## Test Coverage

The platform demonstrates validation of common enterprise API patterns:

- GET – Resource retrieval and validation
- POST – Resource creation with data-driven payloads
- PATCH – Partial updates and field-level validation
- DELETE – Resource cleanup and lifecycle validation
- Positive and basic negative response validation

---

## Configuration Management

All environment-specific values are managed externally via:

`src/test/resources/config.properties`

base.url=https://jsonplaceholder.typicode.com
timeout=5000
auth.token=

No code changes are required to switch environments.

---

## Test Execution

### Run full API regression suite
```bash
mvn clean test
Run specific CRUD operations
bash
Copy code
mvn test -Dgroups=POST
mvn test -Dgroups=DELETE
Run via TestNG suite
bash
Copy code
mvn test -DsuiteXmlFile=testng.xml
