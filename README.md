<!-- AI-SUMMARY-START -->

## 📝 Project Summary

This microservice was automatically generated based on the following high-level requirements:

> Feature: Search Employee by Name
> Input: Employee name (string)
> Output: Employee details matching the provided name
> Constraints:
> - Must be written in Java 21 using Spring Boot 3.5.*
> - Should follow layered architecture (Controller, Service, Repository).
> - Must use Maven for build.
> - Data should be stored in a Mongodb database.
> - Should provide API documentation using Swagger/OpenAPI.
> - Unit and integration tests must be written using JUnit and Mockito.
> - Must provide Dockerfile for containerization.
> - Should include GitHub Actions CI workflow for build, test, and Docker image creation.
> Logic:
> 1. Coordinator enters an employee name into the search field.
> 2. The system queries the database for employees matching the provided name.
> 3. The system displays the details of the matching employee(s).

### 🛠️ Core Dependencies

The following core dependencies were automatically included to support these requirements:

| Group ID | Artifact ID | Scope |
|---|---|---|
| `org.springframework.boot` | `spring-boot-starter-web` | `compile` |
| `org.springframework.boot` | `spring-boot-starter-data-mongodb` | `compile` |
| `org.springframework.boot` | `spring-boot-starter-test` | `test` |
| `org.mockito` | `mockito-core` | `5.11.0` |
| `org.mockito` | `mockito-junit-jupiter` | `5.11.0` |
| `org.springdoc` | `springdoc-openapi-starter-webmvc-ui` | `1.7.0` |
| `org.projectlombok` | `lombok` | `optional` |


<!-- AI-SUMMARY-END -->

# employemanagementsystem