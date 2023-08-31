package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.EnderecoRequest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    private Usuario usuario1;
    private Endereco endereco1;

    @BeforeEach
    void setup() {
        EnderecoRequest enderecoRequest = new EnderecoRequest("12345", "Alguma rua", "Algum bairro", "Alguma cidade", "Algum estado", "Algum complemento", "42");
        endereco1 = new Endereco(enderecoRequest);

        CadastroUsuarioRequest usuarioRequest = new CadastroUsuarioRequest("Volnei", "volnei@email.com", LocalDate.of(1997, 7, 24), enderecoRequest);
        usuario1 = new Usuario(usuarioRequest);

    }

    @Test
    @DisplayName("Should successfully create and initialize a new user in the repository")
    void shouldSuccessfullyCreateAndInitializeNewUser() {

        Usuario usuario = repository.saveAndFlush(usuario1);

        assertNotNull(usuario);
        assertNotNull(usuario.getId());
        assertNotNull(usuario.getEmail());
        assertNotNull(usuario.getNome());
        assertNotNull(usuario.getCriadoEm());
        assertNotNull(usuario.getAtualizadoEm());
        assertNotNull(usuario.getEndereco());
        assertEquals("Volnei", usuario.getNome());
        assertEquals("volnei@email.com", usuario.getEmail());
        assertEquals(LocalDate.of(1997, 7, 24), usuario.getAniversario());
    }

    @Test
    @DisplayName("Should confirm that an existing email is recognized by the repository")
    void shouldConfirmExistingEmailIsRecognizedByRepository() {

        repository.saveAndFlush(usuario1);

        Boolean existsByEmail = repository.existsByEmail(usuario1.getEmail());

        assertTrue(existsByEmail);
    }
}
