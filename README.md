# employemanagementsystem

---
**Date:** 2025-07-25 11:46:08
**Branch:** feature/AG-10_20250725114550
---

## 📝 Project Summary

Feature: Add a new employee to the directory.
Input: Employee details (name, contact information, etc.) via an API endpoint.
Output: Successful creation of the employee record in the MongoDB database and appropriate API response.
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
1.  The Controller should handle the API request to add a new employee.
2.  The Service layer should validate the employee data and call the Repository layer.
3.  The Repository layer should interact with the MongoDB database to store the new employee record.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:1.7.0`
- `org.projectlombok:lombok:optional`
- `org.springframework.boot:spring-boot-devtools:runtime`

--- END ---
