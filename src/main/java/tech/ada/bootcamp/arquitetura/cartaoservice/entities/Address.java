package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.RequestAddress;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponseAddress;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 9)
    private String postalCode;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    private String additionalInfo;

    @Column(nullable = false)
    private String number;

    public Address(RequestAddress request) {
        this.postalCode = request.postalCode();
        this.street = request.street();
        this.neighborhood = request.neighborhood();
        this.city = request.city();
        this.state = request.state();
        this.additionalInfo = request.additionalInfo();
        this.number = request.number();
    }

    public ResponseAddress toResponse() {
        return new ResponseAddress(id, postalCode, street, neighborhood, city, state, additionalInfo, number);
    }
}
