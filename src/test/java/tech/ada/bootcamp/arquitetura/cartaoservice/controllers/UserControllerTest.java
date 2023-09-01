package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.User;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.RequestUser;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.RequestAddress;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponseUser;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UserService;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService service;

    private RequestUser usuarioRequest1;
    private RequestAddress enderecoRequest1;

    @BeforeEach
    void setup() {
        enderecoRequest1 = new RequestAddress("12345", "Alguma rua", "Algum bairro", "Alguma cidade", "Algum estado", "Algum complemento", "42");
        usuarioRequest1 = new RequestUser("Volnei", "volnei@email.com", LocalDate.of(1997, 7, 24), enderecoRequest1);
    }

    @Test
    @DisplayName("Should be able to create a new user")
    void shouldBeAbleToCreateANewUser() throws Exception {
        // Given
        ResponseUser usuarioResponse = new User(usuarioRequest1).toResponse();
        when(service.create(usuarioRequest1)).thenReturn(usuarioResponse);

        // When / Act
        ResultActions response = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(usuarioRequest1)));

        jsonPath("$.name", usuarioResponse.name()).hasJsonPath();

        // Then / Assert
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(usuarioResponse.name()))
                .andExpect(jsonPath("$.email").value(usuarioResponse.email()));
    }
}
