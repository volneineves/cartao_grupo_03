package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CartaoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CartaoResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CriarNovoCartaoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    private CriarNovoCartaoService service;

    public CartaoController(CriarNovoCartaoService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartaoResponse create(@RequestBody CartaoRequest dto){
        return service.execute(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartaoResponse> getAllByUser(@PathVariable UUID id){
        return service.getAllByUser(id);
    }

    @GetMapping("/getOne/{numeroCartao}")
    @ResponseStatus(HttpStatus.OK)
    public CartaoResponse getOne(@PathVariable UUID id){
        return service.getOne(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CartaoResponse edit(@RequestBody CartaoRequest cartaoRequest){
        return service.edit(cartaoRequest);
    }

    @DeleteMapping("/{numeroCartao}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable UUID id){
        return service.delete(id);

    }




}
