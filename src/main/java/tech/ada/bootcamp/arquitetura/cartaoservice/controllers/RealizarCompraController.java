package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.RealizarCompraService;

@RestController
@RequestMapping("/compra")
@Slf4j
@RequiredArgsConstructor
public class RealizarCompraController {

  private final RealizarCompraService service;

    @PostMapping(path = "", produces = "application/json" )
    public CompraResponse realizarCompra(@RequestBody CompraRequest compraRequest){
        return service.execute(compraRequest);
    }
}
