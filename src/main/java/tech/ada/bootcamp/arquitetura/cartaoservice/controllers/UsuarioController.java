package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.UpdateUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UsuarioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CadastroUsuarioResponse> create(@RequestBody @Valid CadastroUsuarioRequest request) {
        CadastroUsuarioResponse response = service.create(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CadastroUsuarioResponse>> findAll() {
        List<CadastroUsuarioResponse> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroUsuarioResponse> findById(@PathVariable UUID id) {
        CadastroUsuarioResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadastroUsuarioResponse> update(@PathVariable UUID id, @RequestBody @Valid UpdateUsuarioRequest request) {
        CadastroUsuarioResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
