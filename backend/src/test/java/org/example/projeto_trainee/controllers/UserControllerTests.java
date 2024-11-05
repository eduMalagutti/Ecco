package org.example.projeto_trainee.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.projeto_trainee.entities.MockEntities;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.example.projeto_trainee.util.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
public class UserControllerTests {

    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private MockEntities mockEntities;

    @BeforeEach
    public void setup() {
        Mockito.doNothing().when(javaMailSender).send(ArgumentMatchers.any(SimpleMailMessage.class));
    }

    @Test
    public void signUpShouldReturnCreatedWhenDataIsValid() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(UserFactory.newUserSignUp());
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/users/signup")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void signUpShouldReturnBadRequestWhenPasswordIsInvalid() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(UserFactory.newUserInvalidPasswordSignUp());
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/users/signup")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void signUpShouldReturnBadRequestWhenEmailIsDuplicated() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(UserFactory.existingUserSignUp());
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/users/signup")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void verifyShouldReturnOkWhenUserTokenExists() throws Exception {
        User notVerifiedUser = userRepository.findByEmail("zoebrown@gmail.com").orElseThrow(
                () -> new NotFoundException("User not found")
        );

        String notVerifiedUserCode = notVerifiedUser.getTokenVerification();

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/verify-email/" + notVerifiedUserCode)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void verifyShouldReturnNotFoundWhenUserTokenIsInvalid() throws Exception {
        String invalidCode = "invalid";
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/verify-email/" + invalidCode)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void forgotPasswordShouldReturnOkWhenEmailExists() throws Exception {
        String existingEmail = UserFactory.existingUserSignIn().getEmail();
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/forgot-password")
                        .queryParam("email", existingEmail)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void forgotPasswordShouldReturnNotFoundWhenEmailDoesNotExist() throws Exception {
        String nonExistingEmail = UserFactory.nonExistingUserSignIn().getEmail();
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/forgot-password")
                        .queryParam("email", nonExistingEmail)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void forgotPasswordVerifyTokenShouldReturnOkWhenTokenIsValid() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(UserFactory.forgotPasswordTokenValid());
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/forgot-password/verify")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void forgotPasswordVerifyTokenShouldReturnBadRequestWhenTokenHasExpired() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(UserFactory.forgotPasswordTokenExpired());
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/forgot-password/verify")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void forgotPasswordVerifyTokenShouldReturnBadRequestWhenTokenIsInvalid() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(UserFactory.forgotPasswordTokenInvalid());
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/forgot-password/verify")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void changePasswordShouldReturnOkWhenDataIsValid() throws Exception {
        String authToken = jwtProvider.createToken(mockEntities.getMockUser1());
        String jsonBody = objectMapper.writeValueAsString(UserFactory.changePasswordDataValid());

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/change-password")
                        .header("Authorization", "Bearer " + authToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
