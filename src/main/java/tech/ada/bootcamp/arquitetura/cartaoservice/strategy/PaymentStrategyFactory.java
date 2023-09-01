package tech.ada.bootcamp.arquitetura.cartaoservice.strategy;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.PaymentMethod;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;

public class PaymentStrategyFactory {
  public static PaymentStrategy getPaymentStrategy(PaymentMethod method) {
    return switch (method) {
      case CREDIT -> new CreditCardPayment();
      case DEBIT -> new DebitCardPayment();
      case PIX -> new PixPayment();
      default -> throw new IllegalArgumentException("Tipo de cartão inválido");
    };
  }
}
