package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.DatabaseException;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.ResourceAlreadyExistsException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.EnderecoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    private CadastroUsuarioRequest usuarioRequest1;
    private EnderecoRequest enderecoRequest1;

    @BeforeEach
    void setup() {
        enderecoRequest1 = new EnderecoRequest("12345", "Alguma rua", "Algum bairro", "Alguma cidade", "Algum estado", "Algum complemento", "42");
        usuarioRequest1 = new CadastroUsuarioRequest("Volnei", "volnei@email.com", LocalDate.of(1997, 7, 24), enderecoRequest1);
    }

    @Test
    @DisplayName("Should be able to create a new user")
    void shouldBeAbleToCreateANewUser() {

        // Given
        given(repository.existsByEmail(anyString())).willReturn(false);
        given(repository.save(any(Usuario.class))).willReturn(new Usuario(usuarioRequest1));

        // Act
        CadastroUsuarioResponse response = service.create(usuarioRequest1);

        // Assert
        assertNotNull(response);
        assertEquals("Volnei", response.nome());
        assertEquals("volnei@email.com", response.email());
        assertEquals(LocalDate.of(1997, 7, 24), response.aniversario());

        verify(repository, times(1)).existsByEmail(anyString());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Should throw error when create a new user with existing email")
    void shouldThrowErrorWhenCreateANewUserWithExistingEmail() {

        // Given
        given(repository.existsByEmail(anyString())).willReturn(true);

        // Act
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            service.create(usuarioRequest1);
        });

        // Verify
        verify(repository, times(1)).existsByEmail(anyString());
    }

    @Test
    @DisplayName("Should throw DatabaseException when database error occurs")
    void shouldThrowDatabaseExceptionWhenDatabaseErrorOccurs() {

        // Given
        given(repository.existsByEmail(anyString())).willReturn(false);
        given(repository.save(any(Usuario.class))).willThrow(DatabaseException.class);

        // Act
        assertThrows(DatabaseException.class, () -> {
            service.create(usuarioRequest1);
        });

        verify(repository, times(1)).existsByEmail(anyString());
        verify(repository, times(1)).save(any(Usuario.class));
    }
}
