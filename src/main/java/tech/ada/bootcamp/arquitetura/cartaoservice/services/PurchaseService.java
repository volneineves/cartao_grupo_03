package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Card;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Purchase;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.DatabaseException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.PurchaseRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponsePurchase;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.PurchaseRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.strategy.PaymentStrategy;

import static tech.ada.bootcamp.arquitetura.cartaoservice.strategy.PaymentStrategyFactory.getPaymentStrategy;

@Service
public class PurchaseService {

    private final PurchaseRepository repository;
    private final CardService cardService;

    public PurchaseService(PurchaseRepository repository, CardService cardService) {
        this.repository = repository;
        this.cardService = cardService;
    }

    public ResponsePurchase execute(PurchaseRequest purchaseRequest) {
        Card card = getCard(purchaseRequest);
        Purchase purchase = new Purchase(purchaseRequest, card);

        PaymentStrategy paymentStrategy = getPaymentStrategy(purchaseRequest.method());
        paymentStrategy.changeStatus(purchase);

        saveOrFail(purchase);
        return purchase.toResponse();
    }

    private void saveOrFail(Purchase purchase) {
        try {
            repository.save(purchase);
        } catch (Exception e) {
            throw new DatabaseException("Error while saving purchase");
        }
    }

    private Card getCard(PurchaseRequest purchaseRequest) {
        return cardService.findOrFailById(purchaseRequest.cardId());
    }
}
