package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "fatura")
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate dataVencimento;

    private LocalDate dataProcessamento;

    private BigDecimal valor;

    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "id_cartao", nullable = false)
    private Cartao cartao;

    public FaturaResponse toResponse() {
        List<CompraResponse> compraResponse = getCompraResponse();
        return new FaturaResponse(id, dataVencimento, dataProcessamento, valor, valorPago, compraResponse);
    }

    public List<CompraResponse> getCompraResponse() {
        return cartao.getCompras().stream().map(Compra::toResponse).toList();
    }
}