package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(

        @Size(max = 9)
        @NotNull
        String cep,

        @NotNull
        String rua,

        @NotNull
        String bairro,

        @NotNull
        String cidade,

        @NotNull
        String estado,

        String complemento,

        @NotNull
        String numero) {
}
