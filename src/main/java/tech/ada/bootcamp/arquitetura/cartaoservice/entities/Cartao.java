package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 16)
    private String numeroCartao;

    @Column(nullable = false)
    private String nomeTitular;

    @Column(nullable = false)
    private LocalDate vencimentoCartao;

    @Column(nullable = false, length = 4)
    private String codigoSeguranca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCartao tipoCartao;

    @Column(nullable = false)
    private String idContaBanco;

    private Boolean dependente = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}