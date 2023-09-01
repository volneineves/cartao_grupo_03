package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.CardType;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponseCard;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 16)
    private String number;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false)
    private LocalDate expiry;

    @Column(nullable = false, length = 4)
    private String securityCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType type;

    @Column(nullable = false)
    private String bankAccountId;

    @Column(nullable = false)
    private Integer closingDay;

    private Boolean isDependent = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
    private List<Purchase> purchases;

    public ResponseCard toResponse() {
        return new ResponseCard(
                this.id,
                this.number,
                this.holderName,
                this.expiry,
                this.securityCode,
                this.type,
                this.bankAccountId,
                this.isDependent,
                this.closingDay,
                this.user.getId()
        );
    }
}