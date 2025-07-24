<!-- AI-SUMMARY-START -->

## 📝 Project Summary

This microservice was automatically generated based on the following high-level requirements:

> Feature: Add new employee
> Input: Employee details (name, contact information, etc.) via an API endpoint.
> Output: Confirmation message upon successful addition of the employee to the directory, along with a unique identifier.
> Constraints: Data must be stored in a MongoDB database; API documentation must be provided using Swagger/OpenAPI; Java 21, Spring Boot 3.5.*, Maven, layered architecture (Controller, Service, Repository), JUnit, Mockito, Dockerfile, GitHub Actions CI workflow must be used.
> Logic:
> 1. Expose an API endpoint for receiving employee details.
> 2. Validate the input data.
> 3. Create a new employee record in the MongoDB database.
> 4. Generate a unique identifier for the employee.
> 5. Return a confirmation message with the unique identifier.

### 🛠️ Core Dependencies

The following core dependencies were automatically included to support these requirements:

| Group ID | Artifact ID | Scope |
|---|---|---|
| `org.springframework.boot` | `spring-boot-starter-web` | `compile` |
| `org.springframework.boot` | `spring-boot-starter-data-mongodb` | `compile` |
| `org.springframework.boot` | `spring-boot-starter-validation` | `compile` |
| `org.springframework.boot` | `spring-boot-starter-test` | `compile` |
| `org.mockito` | `mockito-core` | `compile` |
| `org.mockito` | `mockito-junit-jupiter` | `compile` |
| `org.springdoc` | `springdoc-openapi-starter-webmvc-ui` | `1.7.0` |
| `org.projectlombok` | `lombok` | `1.18.30` |
| `org.springframework.boot` | `spring-boot-devtools` | `runtime` |


<!-- AI-SUMMARY-END -->

# employemanagementsystem

---
**Date:** 2025-07-25 01:05:14
**Branch:** feature/AG-14_20250725010449
---

## 📝 Project Summary

Feature: Employee search by photo.
Input: Employee photo.
Output: List of employees matching the photo, ranked by similarity.
Constraints:
*   Java 21 and Spring Boot 3.5.* must be used.
*   Layered architecture (Controller, Service, Repository) is required.
*   Maven must be used for build.
*   Data must be stored in MongoDB.
*   API documentation via Swagger/OpenAPI is required.
*   JUnit and Mockito must be used for unit and integration tests.
*   Dockerfile for containerization must be provided.
*   GitHub Actions CI workflow for build, test, and Docker image creation is required.
Logic:
1.  Receive the employee photo as input.
2.  Process the photo to extract features for comparison.
3.  Query the MongoDB database to find employees whose stored photo features are similar to the input photo's features.
4.  Rank the matching employees by similarity score.
5.  Return the list of employees, ranked by similarity.

### 🛠️ Core Dependencies

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-mongodb`
- `org.springframework.boot:spring-boot-starter-test`
- `org.mockito:mockito-core`
- `org.springframework.boot:spring-boot-devtools:runtime`
- `org.projectlombok:lombok:1.18.30:optional`
- `org.springframework.boot:spring-boot-starter-actuator`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:1.7.0`

--- END ---
