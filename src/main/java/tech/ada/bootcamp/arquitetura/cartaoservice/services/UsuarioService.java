package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.DatabaseException;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.ResourceAlreadyExistsException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public CadastroUsuarioResponse create(CadastroUsuarioRequest request) {
        Usuario usuario = new Usuario(request);
        checkIfEmailAlreadyExistsByUsuario(usuario);
        saveOrFail(usuario);
        return usuario.toResponse();
    }

    private void saveOrFail(Usuario usuario) {
        try {
            repository.save(usuario);
        } catch (Exception e) {
            throw new DatabaseException("Error while save usuario");
        }
    }

    private void checkIfEmailAlreadyExistsByUsuario(Usuario usuario) {
        boolean isUserExists = repository.existsByEmail(usuario.getEmail());
        if (isUserExists) {
            throw new ResourceAlreadyExistsException("Usuario with the informed email already exists");
        }
    }
}
