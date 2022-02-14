package com.heroes.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heroes.app.HeroeTestUtils;
import com.heroes.app.controller.dto.HeroeRequestDTO;
import com.heroes.app.controller.dto.HeroeResponseDTO;
import com.heroes.app.facade.HeroeFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeroeController.class)
class HeroeControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private HeroeFacade facade;

    @MockBean
    private RestTemplate restTemplate;

    private HeroeController heroeController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        heroeController = new HeroeController(facade);
    }

    @Test
    void postHeroes_201() throws Exception {
        String token = HeroeTestUtils.getToken();

        HeroeRequestDTO request =  HeroeTestUtils.createHeroeRequestDummy();

        HeroeResponseDTO response = HeroeTestUtils.createHeroeResponseDummy();

        Mockito.when(facade.saveHeroe(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/heroes")
                .header("Authorization", token)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}
