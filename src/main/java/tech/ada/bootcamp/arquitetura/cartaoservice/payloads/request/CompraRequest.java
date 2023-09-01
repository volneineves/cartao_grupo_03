package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CompraRequest {
    private UUID idCartao;
    private String loja;
    private BigDecimal valor;
    private PaymentMethod method;
}
