package com.generated.microservice.repository;

import com.generated.microservice.entity.MaterialIssue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ActiveProfiles("test")
public class MaterialIssueRepositoryTest {

    @Autowired
    private MaterialIssueRepository materialIssueRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private MaterialIssue materialIssue1;
    private MaterialIssue materialIssue2;

    @BeforeEach
    void setUp() {
        // Ensure the collection is empty before each test
        try {
            mongoTemplate.dropCollection(MaterialIssue.class);
        } catch (Exception e) {
            System.err.println("Error dropping collection: " + e.getMessage());
        }

        materialIssue1 = new MaterialIssue();
        materialIssue1.setEmployeeId("123");
        materialIssue1.setMaterialName("Laptop");
        materialIssue1.setQuantity(1);
        materialIssue1.setIssueDate(new Date());
        materialIssueRepository.save(materialIssue1);

        materialIssue2 = new MaterialIssue();
        materialIssue2.setEmployeeId("456");
        materialIssue2.setMaterialName("Monitor");
        materialIssue2.setQuantity(2);
        materialIssue2.setIssueDate(new Date());
        materialIssueRepository.save(materialIssue2);
    }

    @AfterEach
    void tearDown() {
        materialIssueRepository.deleteAll();
    }

    @Test
    void findByEmployeeId_ExistingEmployeeId_ReturnsListOfMaterialIssues() {
        List<MaterialIssue> foundMaterials = materialIssueRepository.findByEmployeeId("123");
        assertNotNull(foundMaterials);
        assertEquals(1, foundMaterials.size());
        assertEquals("Laptop", foundMaterials.get(0).getMaterialName());
    }

    @Test
    void findByEmployeeId_NonExistingEmployeeId_ReturnsEmptyList() {
        List<MaterialIssue> foundMaterials = materialIssueRepository.findByEmployeeId("789");
        assertNotNull(foundMaterials);
        assertEquals(0, foundMaterials.size());
    }
}