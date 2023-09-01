package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CartaoResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
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

    @OneToMany(mappedBy = "cartao", fetch = FetchType.EAGER)
    private List<Compra> compras;

    @OneToMany(mappedBy = "cartao")
    private List<Fatura> faturas = new ArrayList<>();

    public Cartao(CartaoResponse dto){
        this.numeroCartao = dto.getNumeroCartao();
        this.nomeTitular = dto.getNomeTitular();
        this.vencimentoCartao = dto.getVencimentoCartao();
        this.codigoSeguranca = dto.getCodigoSeguranca();
        this.tipoCartao = dto.getTipoCartao();
        this.idContaBanco = dto.getIdContaBanco();
        this.dependente = dto.getDependente();
        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        this.usuario = usuario;
    }

    public CartaoResponse dto(){
        CartaoResponse response = new CartaoResponse(
                this.id,
                this.numeroCartao,
                this.nomeTitular,
                this.vencimentoCartao,
                this.codigoSeguranca,
                this.tipoCartao,
                this.idContaBanco,
                this.dependente,
                this.usuario.getId()
        );

        return response;
    }
}
