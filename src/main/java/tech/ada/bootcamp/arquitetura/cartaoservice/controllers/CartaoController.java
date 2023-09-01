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

import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CartaoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CartaoResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CriarNovoCartaoService;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    private CriarNovoCartaoService service;

    public CartaoController(CriarNovoCartaoService service) {
        this.service = service;
    }


    @PostMapping
    public CartaoResponse create(@RequestBody CartaoRequest dto){
        return service.execute(dto);
    }

    @GetMapping("/user/{id}")
    public List<CartaoResponse> getAllByUser(@PathVariable UUID id){
        return service.getAllByUser(id);
    }

    @GetMapping("/{id}")
    public CartaoResponse getOne(@PathVariable UUID id){
        return service.getOne(id);
    }

    @PutMapping("/{id}")
    public CartaoResponse edit(@PathVariable UUID id, @RequestBody CartaoRequest cartaoRequest){
        return service.edit(id, cartaoRequest);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable UUID id){
        return service.delete(id);
    }

}
