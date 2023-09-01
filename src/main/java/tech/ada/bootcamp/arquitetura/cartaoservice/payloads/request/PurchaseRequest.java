package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseRequest(@NotNull UUID cardId, @NotNull String store, @NotNull BigDecimal amount) {
}
