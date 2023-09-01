package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.StatusCompra;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CompraResponse {
    private String numeroCartao;
    private String loja;
    private BigDecimal valor;

    private StatusCompra statusCompra;
}
