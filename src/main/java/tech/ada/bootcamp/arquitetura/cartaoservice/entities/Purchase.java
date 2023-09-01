package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.PurchaseStatus;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.PurchaseRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponsePurchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public PurchaseStatus purchaseStatus;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private String store;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    public Card card;

    public Purchase() {

    }

    public Purchase(PurchaseRequest purchaseRequest, Card card) {
        this.amount = purchaseRequest.amount();
        this.store = purchaseRequest.store();
        this.card = card;
    }

    public ResponsePurchase toResponse() {
        return new ResponsePurchase(card.getId(), store, amount, purchaseStatus);
    }
}
