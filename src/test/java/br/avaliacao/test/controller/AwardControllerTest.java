package br.avaliacao.test.controller;

import com.avaliacao.controller.AwardController;
import com.avaliacao.dto.AwardResultDto;
import com.avaliacao.dto.ProducerIntervalAwardDto;
import com.avaliacao.service.AwardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

public class AwardControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AwardService awardService;

    @InjectMocks
    private AwardController awardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
        mockMvc = MockMvcBuilders.standaloneSetup(awardController).build();

        ProducerIntervalAwardDto minInterval = new ProducerIntervalAwardDto("Producer 1", 1, 2008, 2009);
        ProducerIntervalAwardDto maxInterval = new ProducerIntervalAwardDto("Producer 2", 99, 1900, 1999);
        AwardResultDto response = new AwardResultDto(
            Arrays.asList(minInterval),
            Arrays.asList(maxInterval)
        );

        when(awardService.getAwardWinnersInterval()).thenReturn(response);
    }

    @Test
    public void testGetAwardIntervals() throws Exception {
        mockMvc.perform(get("/api/awards/intervals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min").isArray())
                .andExpect(jsonPath("$.min[0].producer", is("Producer 1")))
                .andExpect(jsonPath("$.max").isArray())
                .andExpect(jsonPath("$.max[0].producer", is("Producer 2")));
    }
}