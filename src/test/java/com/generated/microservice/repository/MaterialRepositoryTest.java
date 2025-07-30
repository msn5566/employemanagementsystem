package com.generated.microservice.repository;

import com.generated.microservice.entity.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=7.0.9")
public class MaterialRepositoryTest {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Material material1;
    private Material material2;

    @BeforeEach
    public void setUp() {
        try {
            mongoTemplate.dropCollection(Material.class);
        } catch (Exception e) {
            System.err.println("Error dropping collection: " + e.getMessage());
        }

        material1 = new Material();
        material1.setItemName("Laptop");
        material1.setIssueDate("2024-01-01");
        material1.setNotes("For development");
        material1.setEmployeeId("123");

        material2 = new Material();
        material2.setItemName("Mouse");
        material2.setIssueDate("2024-01-05");
        material2.setNotes("For development");
        material2.setEmployeeId("123");

        materialRepository.save(material1);
        materialRepository.save(material2);
    }

    @AfterEach
    public void tearDown() {
        try {
            mongoTemplate.dropCollection(Material.class);
        } catch (Exception e) {
            System.err.println("Error dropping collection: " + e.getMessage());
        }
    }

    @Test
    public void testFindByEmployeeId() {
        List<Material> materials = materialRepository.findByEmployeeId("123");
        assertNotNull(materials);
        assertEquals(2, materials.size());
        assertEquals("Laptop", materials.get(0).getItemName());
        assertEquals("Mouse", materials.get(1).getItemName());
    }

    @Test
    public void testFindByEmployeeId_noMaterials() {
        List<Material> materials = materialRepository.findByEmployeeId("456");
        assertNotNull(materials);
        assertEquals(0, materials.size());
    }
}