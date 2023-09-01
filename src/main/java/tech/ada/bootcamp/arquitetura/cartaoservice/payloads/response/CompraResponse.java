package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.StatusCompra;

import java.math.BigDecimal;

public record CompraResponse(String numeroCartao, String loja, BigDecimal valor, StatusCompra statusCompra) {

}
