package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.InvoiceResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.InvoiceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getById(@PathVariable("id") UUID id) {
        InvoiceResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getAllByUserId(@RequestParam UUID idUser) {
        List<InvoiceResponse> response = service.findAllByUser(idUser);
        return ResponseEntity.ok(response);
    }
}
