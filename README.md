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
**Date:** 2025-08-03 18:58:32
**Branch:** feature/AG-18_20250803185759
---

## 📝 Project Summary

Feature: Data mapping from source XML to target XML via CSV configuration.
Input:
  - source.xml: Source XML data.
  - expected_target.xml: Expected target XML structure.
  - mapping_with_validation.csv: CSV file containing mapping rules and validations.
Output: A POJO (Plain Old Java Object) representing the data mapped from source.xml to match the structure of expected_target.xml, according to the rules defined in mapping_with_validation.csv.
Constraints:
  - The mapping must adhere to the rules and validations specified in the mapping_with_validation.csv file.
Logic:
  - Read data from source.xml.
  - Read mapping rules from mapping_with_validation.csv.
  - Transform and map data from source.xml based on the mapping rules to fit the expected_target.xml structure.
  - Create a POJO representing the transformed data.

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
- `org.apache.commons:commons-csv:1.10.0`
- `com.fasterxml.jackson.dataformat:jackson-dataformat-xml`

--- END ---
