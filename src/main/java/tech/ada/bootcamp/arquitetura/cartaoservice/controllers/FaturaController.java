package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.FaturaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/faturas")
public class FaturaController {

    private final FaturaService service;

    public FaturaController(FaturaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturaResponse> getById(@PathVariable("id") UUID id) {
        FaturaResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<FaturaResponse>> getAllByUserId(@PathVariable("id") UUID idUser) {
        List<FaturaResponse> response = service.findAllByUsuario(idUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> create() {
        service.createInvoices();
        return ResponseEntity.ok().build();
    }
}
