package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import java.math.BigDecimal;
import java.util.UUID;

public record TotalComprasProjection(UUID idCartao, BigDecimal valorTotal) {
}
