package tech.ada.bootcamp.arquitetura.cartaoservice.strategy;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.PaymentMethod;

public class PaymentStrategyFactory {
  public static PaymentStrategy getPaymentStrategy(PaymentMethod method) {
    return switch (method) {
      case CREDIT -> new CreditCardPayment();
      case DEBIT -> new DebitCardPayment();
      case PIX -> new PixPayment();
      default -> throw new IllegalArgumentException("Invalid card type");
    };
  }
}
