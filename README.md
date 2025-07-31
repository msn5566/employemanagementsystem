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
**Date:** 2025-08-01 00:41:58
**Branch:** feature/AG-18_20250801004116
---

## 📝 Project Summary

Feature: Data transformation and persistence from XML to MongoDB.
Input: source.xml, expected_target.xml, mapping_with_validation.csv
Output: A POJO created from source.xml and expected_target.xml data, and a MongoDB document containing the source data. A MongoDB collection will be created if it doesn't exist.
Constraints: Data mapping must adhere to the rules defined in mapping_with_validation.csv. The application must connect to a MongoDB instance.
Logic:
1. Create a POJO using data from source.xml and expected_target.xml.
2. Map data from source.xml to the format expected by expected_target.xml using mapping_with_validation.csv.
3. Connect to MongoDB.
4. Check if the designated collection exists. If not, create it.
5. Create a document in MongoDB using data from source.xml.

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
- `com.fasterxml.jackson.dataformat:jackson-dataformat-xml`
- `org.apache.commons:commons-csv:1.10.0`

--- END ---
