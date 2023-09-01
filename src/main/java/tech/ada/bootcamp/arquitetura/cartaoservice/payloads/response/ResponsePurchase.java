package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.PurchaseStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record ResponsePurchase(UUID cardId, String store, BigDecimal amount, PurchaseStatus purchaseStatus) {

}
