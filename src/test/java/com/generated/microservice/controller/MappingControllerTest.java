package com.generated.microservice.controller;

import com.generated.microservice.service.MappingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MappingControllerTest {

    @Mock
    private MappingService mappingService;

    @InjectMocks
    private MappingController mappingController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mappingController).build();
    }

    @Test
    public void testProcessMapping_success() throws Exception {
        MockMultipartFile sourceXml = new MockMultipartFile("sourceXml", "source.xml", MediaType.APPLICATION_XML_VALUE, "<root><field>value</field></root>".getBytes());
        MockMultipartFile mappingCsv = new MockMultipartFile("mappingCsv", "mapping.csv", MediaType.TEXT_PLAIN_VALUE, "source_field,target_field\nfield,target".getBytes());

        when(mappingService.mapData(any(), any())).thenReturn(Collections.singletonMap("target", "value"));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/mapping/process")
                        .file(sourceXml)
                        .file(mappingCsv))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testProcessMapping_failure() throws Exception {
        MockMultipartFile sourceXml = new MockMultipartFile("sourceXml", "source.xml", MediaType.APPLICATION_XML_VALUE, "<root><field>value</field></root>".getBytes());
        MockMultipartFile mappingCsv = new MockMultipartFile("mappingCsv", "mapping.csv", MediaType.TEXT_PLAIN_VALUE, "source_field,target_field\nfield,target".getBytes());

        when(mappingService.mapData(any(), any())).thenThrow(new RuntimeException("Test exception"));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/mapping/process")
                        .file(sourceXml)
                        .file(mappingCsv))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}