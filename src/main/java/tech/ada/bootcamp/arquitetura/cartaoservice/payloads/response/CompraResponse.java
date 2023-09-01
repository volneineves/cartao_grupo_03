package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.StatusCompra;

import java.math.BigDecimal;
import java.util.UUID;

public record CompraResponse(UUID idCartao, String loja, BigDecimal valor, StatusCompra statusCompra) {

}
