package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<List<FaturaResponse>> getAllByUserId(@RequestParam UUID idUser) {
        List<FaturaResponse> response = service.findAllByUsuario(idUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> create() {
        service.createInvoices();
        return ResponseEntity.ok().build();
    }
}
