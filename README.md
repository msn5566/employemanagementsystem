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
**Date:** 2025-07-28 01:24:07
**Branch:** feature/AG-15_20250728012344
---

## 📝 Project Summary

Feature: Add a reward to an employee's profile.
Input: Employee ID, reward details (e.g., description, date, value).
Output: Confirmation message indicating the reward was successfully added to the employee's profile, and updated employee profile data.
Constraints: Data must be stored in a MongoDB database. API documentation should be provided via Swagger/OpenAPI.
Logic: The system must validate the employee ID. The system will store the reward details associated with the specified employee. The data access layer should handle interactions with the MongoDB database.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.boot:spring-boot-devtools:runtime`
- `org.projectlombok:lombok:optional`
- `org.springframework.boot:spring-boot-configuration-processor:optional`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0`

--- END ---
