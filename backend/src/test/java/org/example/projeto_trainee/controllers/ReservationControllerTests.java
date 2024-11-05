package org.example.projeto_trainee.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.projeto_trainee.entities.MockEntities;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.util.ReservationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles (profiles = "test")
public class ReservationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockEntities mockEntities;

    @Autowired
    private JWTProvider jwtProvider;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void createReservationShouldReturnCreatedWhenDataIsValid() throws Exception {
        String token = jwtProvider.createToken(mockEntities.getMockUser1());

        String jsonBody = objectMapper.writeValueAsString(ReservationFactory.createNewValidReservation(
                mockEntities.getMockServiceEntity()
        ));
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations/create")
                        .header("Authorization", "Bearer " + token)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createReservationShouldReturnNotFoundWhenUserIsNotFound() throws Exception {
        String token = jwtProvider.createMockInvalidToken();

        String jsonBody = objectMapper.writeValueAsString(ReservationFactory.invalidUserReservation(
                mockEntities.getMockServiceEntity()
        ));
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations/create")
                        .header("Authorization", "Bearer " + token)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createReservationShouldReturnNotFoundWhenServiceNotFound() throws Exception {
        String token = jwtProvider.createToken(mockEntities.getMockUser1());

        String jsonBody = objectMapper.writeValueAsString(ReservationFactory.invalidServiceReservation(
                mockEntities.getMockServiceEntity()
        ));
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations/create")
                        .header("Authorization", "Bearer " + token)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Service not found"));
    }

    @Test
    public void createReservationShouldReturnBadRequestWhenTimeIsInvalid() throws Exception {
        String token = jwtProvider.createToken(mockEntities.getMockUser1());

        String jsonBody = objectMapper.writeValueAsString(ReservationFactory.invalidTimeReservation(
                mockEntities.getMockServiceEntity()
        ));
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations/create")
                        .header("Authorization", "Bearer " + token)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createReservationShouldReturnBadRequestWhenAddressIsInvalid() throws Exception {
        String token = jwtProvider.createToken(mockEntities.getMockUser1());

        String jsonBody = objectMapper.writeValueAsString(ReservationFactory.invalidAddressReservation(
                mockEntities.getMockServiceEntity()
        ));
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations/create")
                        .header("Authorization", "Bearer " + token)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
