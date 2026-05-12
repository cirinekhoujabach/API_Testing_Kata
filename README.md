# 📌 API Testing Framework – Clean Architecture (Restful Booker)

## 🧾 Overview

The objective was to design and build a maintainable test suite using Java, Rest-Assured, and Cucumber, focusing on validating the behavior and responses of a hotel booking API.
This project is a **scalable API test automation framework** designed using **Clean Architecture principles**.

It validates the behavior of the **Restful Booker API** through:

- ✅ Functional scenarios
- ✅ Business rules validation
- ✅ Negative testing
- ✅ Contract validation (JSON Schema)

The goal is to ensure **maintainability, readability, and scalability** of API tests.

---

## 🏗️ Project Structure

    src
    ├── main/java/com.booking
    │
    │   ├── client/        # HTTP layer (RestAssured wrapper)
    │   ├── endpoints/     # API routes definition
    │   ├── services/      # Business actions (use cases)
    │   ├── models/        # DTOs (request/response objects)
    │   ├── factory/       # Test data builders
    │   ├── context/       # Shared state between steps
    │   ├── configs/       # Environment configuration
    │   ├── validations/   # Custom validators
    │   └── utils/         # Helpers
    │
    ├── test/java/com.booking
    │   ├── stepdefinition/  # Glue between Gherkin and code
    │   ├── hooks/           # Setup / teardown
    │   └── runner/          # Test execution
    │
    └── test/resources
        ├── features/        # BDD scenarios
        ├── schemas/         # JSON schema contracts
        └── config/          # config.properties

---

## 🔄 Data Flow (Execution)

    Feature → StepDefinition → Service → Endpoint → RestClient → API
                                                          ↓
                                       Response → Validation → Assertion

---

## 🧪 Testing Strategy

### 1. Behavior-Driven Development (BDD)

Readable scenarios written in **Gherkin**:

    Scenario: Create and retrieve a valid booking
      When I create a booking with valid data
      Then the booking is successfully created

---

### 2. Test Data Management

Centralized in **BookingFactory**

Supports:
- Valid data
- Edge cases
- Negative scenarios

---

### 3. Validation Layers

#### 🔹 Technical Validation
- Status codes
- Response time (optional)

#### 🔹 Contract Validation
- JSON Schema validation

---

## 🔐 Authentication Strategy

- Token retrieved via `/auth`
- Stored in `TestContext`
- Injected automatically into secured requests

---

## 🎯 Key Design Decisions

### ✔ Separation of Concerns

Each layer has a single responsibility:

- Steps → behavior
- Services → business logic
- Client → HTTP communication

---

### ✔ Reusability

- Shared `TestContext`
- Centralized factories
- Generic REST client

---

### ✔ Scalability

New APIs can be added without impacting existing layers.

---

### ✔ Maintainability

- Clear structure
- Low coupling
- High readability

---

