package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.CardType;

import java.time.LocalDate;
import java.util.UUID;

public record ResponseCard(UUID id,
                           String number,
                           String holderName,
                           LocalDate expiry,
                           String securityCode,
                           CardType type,
                           String bankAccountId,
                           Boolean isDependent,
                           Integer closingDay,
                           UUID userId) {

}
