package com.generated.microservice.controller;

import com.generated.microservice.service.ExternalApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExternalApiControllerTest {

    @Mock
    private ExternalApiService externalApiService;

    @InjectMocks
    private ExternalApiController externalApiController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(externalApiController).build();
    }

    @Test
    void synchronizeData_shouldCallFetchAndSaveData() throws Exception {
        mockMvc.perform(post("/external-api/sync"))
                .andExpect(status().isOk());

        verify(externalApiService, times(1)).fetchAndSaveData();
    }
}