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
**Date:** 2025-08-06 21:11:46
**Branch:** feature/AG-18_20250806211022
---

## 📝 Project Summary

Feature: Generate a target XML file by mapping data from a source POJO to a target POJO based on a JSON mapping file.
Input: source.xml, expected_target.xml, mapping_with_validation.json
Output: target_{current timestamp}.xml
Constraints: Output filename should be "target_" appended with the current timestamp. Mapping should adhere to the rules specified in mapping_with_validation.json.
Logic:
1. Create source POJO from source.xml.
2. Create target POJO from expected_target.xml.
3. Map data from source POJO to target POJO according to mapping_with_validation.json.
4. Generate target XML file named target_{current timestamp} from the mapped target POJO.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.boot:spring-boot-devtools:optional`
- `org.projectlombok:lombok:1.18.30:optional`
- `org.springframework.boot:spring-boot-starter-validation`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0`
- `org.slf4j:slf4j-api`
- `ch.qos.logback:logback-classic`
- `jakarta.xml.bind:jakarta.xml.bind-api`
- `com.fasterxml.jackson.core:jackson-databind`
- `com.fasterxml.jackson.dataformat:jackson-dataformat-xml`

--- END ---
