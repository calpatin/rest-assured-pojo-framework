# Rest Assured POJO Framework (Learning Project)

## Overview

This project is a **learning-focused API testing framework** built with **Java 17**, **Rest Assured**, and **TestNG**.
Its main goal is to demonstrate **core API testing concepts** in a clean, extensible, and professional structure.

The framework uses the public API **[https://dummyjson.com](https://dummyjson.com)** and focuses on:

* JSON response validation
* Data extraction using JsonPath
* Mapping responses to POJOs (Jackson)
* Clean separation of responsibilities

This project is intentionally written step by step to be easy to understand and extend.

---

## Tech Stack

* **Java 17**
* **Maven** (build & dependency management)
* **Rest Assured** – API testing library
* **TestNG** – test framework
* **Jackson** – JSON ↔ POJO mapping
* **Hamcrest** – assertions

---

## Project Structure

```
src
├── main
│   └── java
│       └── models
│           └── response
│               ├── UsersResponse.java
│               ├── User.java
│               ├── Address.java
│               ├── Company.java
│               ├── Bank.java
│               └── Crypto.java
│
└── test
    └── java
        ├── base
        │   └── BaseTest.java
        │
        └── tests
            └── json
                ├── ExtractUsersTest.java
                └── UsersPojoTest.java
```

---

## What Has Been Implemented So Far

### 1. Base Test Configuration

**`BaseTest`** is responsible for common test setup:

* Setting the Rest Assured base URI
* Centralizing configuration logic

All test classes extend `BaseTest`, ensuring consistency and avoiding duplication.

---

### 2. JSON Response Validation

We validate API responses using:

* HTTP status code assertions
* JsonPath expressions
* Hamcrest matchers

Example concepts covered:

* Extracting lists from JSON (`users.email`)
* Validating formats using regex (e.g. email validation)

---

### 3. POJO Mapping (Deserialization)

Complex JSON responses are mapped to Java objects using **Jackson**.

Key concepts:

* One POJO per logical JSON object
* Nested objects mapped to nested POJOs
* Use of `@JsonIgnoreProperties(ignoreUnknown = true)` to handle extra fields safely

This allows:

* Strong typing
* Cleaner assertions
* Easier maintenance when the API evolves

---

### 4. Example Tests

#### `ExtractUsersTest`

* Demonstrates raw JsonPath extraction
* Validates that all returned emails match a regex pattern

#### `UsersPojoTest`

* Deserializes the full response into `UsersResponse`
* Asserts on strongly-typed Java objects
* Shows the recommended approach for complex APIs

---

## How to Run the Tests

### From IntelliJ IDEA

* Right-click on a test class
* Select **Run**

### From command line

```bash
mvn clean test
```

---

## Learning Goals

This framework is designed to help you learn:

* How Rest Assured works under the hood
* When to use JsonPath vs POJO mapping
* How to structure API test frameworks professionally
* How to design code that is easy to extend and refactor
* Common pitfalls when validating complex JSON responses

---

## Next Planned Steps

* Introduce **API Client classes** (separation of HTTP logic)
* Add **negative test cases**
* Parameterize tests
* Add request models (POST / PUT)
* Improve reporting and logging

---

## Disclaimer

This project is intended for **learning and practice purposes** and uses a public dummy API.

---

Happy testing 🚀
