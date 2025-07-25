# employemanagementsystem

---
**Date:** 2025-07-25 11:59:31
**Branch:** feature/AG-10_20250725115705
---

## 📝 Project Summary

Feature: Add new employee details to the directory.
Input: Employee details (name, contact information, etc.) via an API endpoint.
Output: Confirmation message indicating successful addition of the employee, and the employee's details stored in the database.
Constraints:
*   Must be written in Java 21 using Spring Boot 3.5.*
*   Follow layered architecture (Controller, Service, Repository).
*   Use Maven for build.
*   Data should be stored in a MongoDB database.
*   Provide API documentation using Swagger/OpenAPI.
*   Unit and integration tests must be written using JUnit and Mockito.
*   Must provide Dockerfile for containerization.
*   Should include GitHub Actions CI workflow for build, test, and Docker image creation.
Logic:
1.  Receive employee details via API endpoint (Controller).
2.  Validate the input data (Service).
3.  Store the employee details in the MongoDB database (Repository).
4.  Return a confirmation message.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springframework.boot:spring-boot-starter-test`
- `org.projectlombok:lombok:1.18.30:optional`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:1.7.0`
- `org.springframework.boot:spring-boot-devtools:runtime`

--- END ---
