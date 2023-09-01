package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Address;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.User;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.DatabaseException;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.ResourceAlreadyExistsException;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.ResourceNotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.RequestUser;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponseUser;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<ResponseUser> findAll() {
        return repository.findAll().stream()
                .map(User::toResponse)
                .toList();
    }

    public ResponseUser findById(UUID id) {
        User user = findOrFailById(id);
        return user.toResponse();
    }

    public User findOrFailById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User could not be found"));
    }

    public ResponseUser create(RequestUser request) {
        User user = new User(request);
        checkIfEmailAlreadyExists(user);
        saveOrFail(user);
        return user.toResponse();
    }

    public ResponseUser update(UUID id, RequestUser request) {
        Address address = new Address(request.address());
        User user = findOrFailById(id);

        user.setName(request.name());
        user.setEmail(request.email());
        user.setBirthday(request.birthday());
        user.setAddress(address);

        saveOrFail(user);
        return user.toResponse();
    }

    public void delete(UUID id) {
        User user = findOrFailById(id);
        repository.delete(user);
    }

    private void saveOrFail(User user) {
        try {
            repository.save(user);
        } catch (Exception e) {
            throw new DatabaseException("Error while saving user");
        }
    }

    private void checkIfEmailAlreadyExists(User user) {
        boolean isUserExists = repository.existsByEmail(user.getEmail());
        if (isUserExists) {
            throw new ResourceAlreadyExistsException("User with the informed email already exists");
        }
    }
}
