package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompraRequest {
    private String numeroCartao;
    private String loja;
    private BigDecimal valor;
}
