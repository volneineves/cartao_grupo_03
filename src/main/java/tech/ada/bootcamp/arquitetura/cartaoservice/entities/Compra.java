package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import org.hibernate.annotations.CreationTimestamp;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal valor;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public StatusCompra statusCompra;
    
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dataCompra;

    @Column(nullable = false)
    private String loja;

    @ManyToOne
    @JoinColumn(name = "id_cartao", nullable = false)
    public Cartao cartao;


    public Compra() {

    }

    public Compra(CompraRequest compraRequest) {
        this.valor = compraRequest.getValor();
        this.loja = compraRequest.getLoja();
        Cartao cartao = new Cartao();
        cartao.setId(compraRequest.getIdCartao());
        this.cartao = cartao;
        this.dataCompra = LocalDateTime.now();
        this.statusCompra = StatusCompra.PENDENTE;
    }

    public CompraResponse toResponse() {
        return new CompraResponse(cartao.getId(), loja, valor, statusCompra);
    }
}
