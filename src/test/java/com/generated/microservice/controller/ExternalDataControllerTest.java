package com.generated.microservice.controller;

import com.generated.microservice.service.ExternalDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExternalDataControllerTest {

    @Mock
    private ExternalDataService externalDataService;

    @InjectMocks
    private ExternalDataController externalDataController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(externalDataController).build();
    }

    @Test
    public void synchronizeData_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/external-data/sync"))
                .andExpect(status().isOk());

        verify(externalDataService, times(1)).synchronizeData();
    }
}