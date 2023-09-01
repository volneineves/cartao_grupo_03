package tech.ada.bootcamp.arquitetura.cartaoservice.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CartaoNaoEncontradoException extends EntityNotFoundException {

    public CartaoNaoEncontradoException() {
        super("Este cartão não foi encontrado no banco de dados.");
    }
}