package tech.ada.bootcamp.arquitetura.cartaoservice.strategy;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Purchase;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.PurchaseStatus;

import java.math.BigDecimal;
import java.util.UUID;

import static tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.PurchaseStatus.APPROVED;
import static tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.PurchaseStatus.DENIED;

public class PixPayment implements PaymentStrategy {

    @Override
    public void changeStatus(Purchase purchase) {
        boolean isApprovedPurchase = checkWithBank(purchase.getId(), purchase.getAmount());
        PurchaseStatus purchaseStatus = checkPurchaseStatus(isApprovedPurchase);
        purchase.setPurchaseStatus(purchaseStatus);
    }

    private PurchaseStatus checkPurchaseStatus(Boolean isApprovedPurchase) {
        if (isApprovedPurchase) {
            return APPROVED;
        } else {
            return DENIED;
        }
    }


    private boolean checkWithBank(UUID idCartao, BigDecimal valor) {
        System.out.println("COMPRA COM O PIX");
        return true;
    }
}
