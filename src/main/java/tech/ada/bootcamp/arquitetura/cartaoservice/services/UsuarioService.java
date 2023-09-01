package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.DatabaseException;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.ResourceAlreadyExistsException;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.ResourceNotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.UpdateUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<CadastroUsuarioResponse> findAll() {
        return repository.findAll().stream()
                .map(Usuario::toResponse)
                .toList();
    }

    public CadastroUsuarioResponse findById(UUID id) {
        Usuario usuario = findOrFailById(id);
        return usuario.toResponse();
    }

    private Usuario findOrFailById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
    }

    public CadastroUsuarioResponse create(CadastroUsuarioRequest request) {
        Usuario usuario = new Usuario(request);
        checkIfEmailAlreadyExists(usuario);
        saveOrFail(usuario);
        return usuario.toResponse();
    }

    public CadastroUsuarioResponse update(UUID id, UpdateUsuarioRequest request) {
        Usuario usuario = findOrFailById(id);
        usuario.update(request);
        saveOrFail(usuario);
        return usuario.toResponse();
    }

    public void delete(UUID id) {
        Usuario usuario = findOrFailById(id);
        repository.delete(usuario);
    }

    private void saveOrFail(Usuario usuario) {
        try {
            repository.save(usuario);
        } catch (Exception e) {
            throw new DatabaseException("Error while saving usuario");
        }
    }

    private void checkIfEmailAlreadyExists(Usuario usuario) {
        boolean isUserExists = repository.existsByEmail(usuario.getEmail());
        if (isUserExists) {
            throw new ResourceAlreadyExistsException("Usuario with the informed email already exists");
        }
    }
}
