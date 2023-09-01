package tech.ada.bootcamp.arquitetura.cartaoservice.strategy;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Purchase;

public interface PaymentStrategy {
    void changeStatus(Purchase purchase);
}