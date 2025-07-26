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
**Date:** 2025-07-27 01:32:41
**Branch:** feature/AG-14_20250727013220
---

## 📝 Project Summary

Feature: Employee search by photo
Input: Employee photo
Output: List of employees matching the photo
Constraints:
* Must be written in Java 21 using Spring Boot 3.5.*
* Should follow layered architecture (Controller, Service, Repository).
* Must use Maven for build.
* Data should be stored in a Mongodb database.
* Should provide API documentation using Swagger/OpenAPI.
* Unit and integration tests must be written using JUnit and Mockito.
* Must provide Dockerfile for containerization.
* Should include GitHub Actions CI workflow for build, test, and Docker image creation.
Logic: The system should analyze the input photo and compare it against the photos stored in the database, returning a list of employees with matching photos.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:1.7.0`
- `org.springframework.boot:spring-boot-starter-test`
- `org.mockito:mockito-core`
- `org.projectlombok:lombok:optional`
- `org.springframework.boot:spring-boot-devtools:runtime`

--- END ---
