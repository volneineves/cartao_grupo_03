package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.PurchaseRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponsePurchase;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.PurchaseService;

@RestController
@RequestMapping("/purchases")
@Slf4j
@RequiredArgsConstructor
public class PurchaseController {

  private final PurchaseService service;

    @PostMapping(path = "", produces = "application/json" )
    public ResponsePurchase doPurchase(@RequestBody @Valid PurchaseRequest purchaseRequest){
        return service.execute(purchaseRequest);
    }
}
