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
**Date:** 2025-07-28 02:36:03
**Branch:** feature/AG-15_20250728023536
---

## 📝 Project Summary

Feature: Add employee reward functionality.
Input: Employee ID, reward details (e.g., name, description, date).
Output: Confirmation of reward creation associated with the specified employee.
Constraints: Data must be stored in a MongoDB database. API documentation should be available via Swagger/OpenAPI.
Logic: Implement a REST endpoint that allows a manager to create a reward for an employee. The endpoint should validate the input data and store the reward information in the database, linking it to the employee's profile. The implementation must be in Java 21, Spring Boot 3.5.*, using a layered architecture. Unit and integration tests are required. The project must be buildable with Maven and include a Dockerfile and GitHub Actions CI workflow.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springframework.boot:spring-boot-starter-test:test`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:1.7.0`
- `org.projectlombok:lombok:optional`
- `org.springframework.boot:spring-boot-devtools:runtime`

--- END ---
