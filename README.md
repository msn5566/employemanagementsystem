# employemanagementsystem

---
**Date:** 2025-07-25 12:14:49
**Branch:** feature/AG-10_20250725121431
---

## 📝 Project Summary

Feature: Add new employee to the employee directory.
Input: Employee details (name, contact information, etc.) via API.
Output: Confirmation of successful employee addition, including a unique employee ID.
Constraints:
*   Must be written in Java 21 using Spring Boot 3.5.*
*   Should follow layered architecture (Controller, Service, Repository).
*   Must use Maven for build.
*   Data should be stored in a Mongodb database.
*   Should provide API documentation using Swagger/OpenAPI.
*   Unit and integration tests must be written using JUnit and Mockito.
*   Must provide Dockerfile for containerization.
*   Should include GitHub Actions CI workflow for build, test, and Docker image creation.
Logic:
1.  Expose a REST endpoint to receive employee details.
2.  Validate the input data.
3.  Store the employee data in the MongoDB database.
4.  Generate a unique employee ID.
5.  Return a success response with the generated employee ID.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.boot:spring-boot-devtools`
- `org.projectlombok:lombok:1.18.30:optional`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:1.7.0`

--- END ---


---
**Date:** 2025-08-03 19:29:14
**Branch:** feature/AG-18_20250803192838
---

## 📝 Project Summary

Feature: POJO creation and data mapping from source XML to target XML.
Input:
  - source.xml: Input XML file containing employee data.
  - expected_target.xml: Expected output XML file structure.
  - mapping_with_validation.json: JSON file defining the data mapping and validation rules between source and target XML.
Output:
  - A POJO (Plain Old Java Object) representing the data mapped according to the `mapping_with_validation.json` from `source.xml` to match the structure of `expected_target.xml`.
Constraints:
  - Data mapping must adhere to the rules defined in `mapping_with_validation.json`.
  - The created POJO should accurately represent the transformed data.
Logic:
  - Read data from `source.xml`.
  - Apply the data mapping and validation rules defined in `mapping_with_validation.json`.
  - Create a POJO with the transformed data, conforming to the structure of `expected_target.xml`.
  - The project's source code should be located in the "development" branch of the GitHub repository "employemanagementsystem" at the provided GitHub URL.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.boot:spring-boot-devtools`
- `org.projectlombok:lombok:1.18.30:optional`
- `org.springframework.boot:spring-boot-starter-validation`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0`
- `org.slf4j:slf4j-api`
- `ch.qos.logback:logback-classic`
- `com.fasterxml.jackson.dataformat:jackson-dataformat-xml`
- `com.fasterxml.jackson.core:jackson-databind`
- `org.json:json:20240303`

--- END ---
