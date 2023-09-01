package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Address;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.User;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.RequestUser;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.RequestAddress;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private User user1;
    private Address address1;

    @BeforeEach
    void setup() {
        RequestAddress enderecoRequest = new RequestAddress("12345", "Alguma rua", "Algum bairro", "Alguma cidade", "Algum estado", "Algum complemento", "42");
        address1 = new Address(enderecoRequest);

        RequestUser usuarioRequest = new RequestUser("Volnei", "volnei@email.com", LocalDate.of(1997, 7, 24), enderecoRequest);
        user1 = new User(usuarioRequest);

    }

    @Test
    @DisplayName("Should successfully create and initialize a new user in the repository")
    void shouldSuccessfullyCreateAndInitializeNewUser() {

        User user = repository.saveAndFlush(user1);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getEmail());
        assertNotNull(user.getName());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertNotNull(user.getAddress());
        assertEquals("Volnei", user.getName());
        assertEquals("volnei@email.com", user.getEmail());
        assertEquals(LocalDate.of(1997, 7, 24), user.getBirthday());
    }

    @Test
    @DisplayName("Should confirm that an existing email is recognized by the repository")
    void shouldConfirmExistingEmailIsRecognizedByRepository() {

        repository.saveAndFlush(user1);

        Boolean existsByEmail = repository.existsByEmail(user1.getEmail());

        assertTrue(existsByEmail);
    }
}
