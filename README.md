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
**Date:** 2025-07-30 21:18:53
**Branch:** feature/AG-22_20250730211754
---

## 📝 Project Summary

Feature: Data Synchronization from External API to Database
Input:
    * OAuth credentials for accessing the external API.
    * URL of the external API endpoint.
Output:
    * Data fetched from the external API as a list.
    * The fetched data persisted into the database.
Constraints:
    * Must use OAuth for authorization.
    * The user must have admin privileges to initiate the data fetch.
Logic:
    1. Authenticate with the external API using OAuth credentials.
    2. Fetch data from the specified API endpoint.
    3. Transform the API response into a suitable format for database insertion.
    4. Insert the transformed data into the database.

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
- `org.springframework.security:spring-security-oauth2-client`
- `org.springframework.boot:spring-boot-starter-security`
- `org.springframework:spring-webflux`

--- END ---
