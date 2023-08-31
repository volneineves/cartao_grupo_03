package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import java.util.UUID;

public record EnderecoResponse(
        UUID id,
        String cep,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String complemento,
        String numero) {
}
