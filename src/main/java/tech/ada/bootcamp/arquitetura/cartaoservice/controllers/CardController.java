package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.RequestCard;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponseCard;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseCard create(@RequestBody RequestCard dto){
        return service.create(dto);
    }

    @GetMapping("/user/{id}")
    public List<ResponseCard> getAllByUser(@PathVariable UUID id){
        return service.getAllByUser(id);
    }

    @GetMapping("/{id}")
    public ResponseCard getOne(@PathVariable UUID id){
        return service.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseCard edit(@PathVariable UUID id, @RequestBody RequestCard cartaoRequest){
        return service.update(id, cartaoRequest);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable UUID id){
        return service.delete(id);
    }

}
