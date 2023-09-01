package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.CardType;

import java.time.LocalDate;
import java.util.UUID;

public record ResponseCard(UUID id,
                           String cardNumber,
                           String cardholderName,
                           LocalDate cardExpirationDate,
                           String securityCode,
                           CardType cardType,
                           String bankAccountId,
                           Boolean isDependent,
                           UUID userId) {

}
