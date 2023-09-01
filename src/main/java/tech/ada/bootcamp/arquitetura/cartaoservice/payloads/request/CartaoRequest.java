package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CartaoRequest {

    //TODO validacoes de campos

    private String numeroCartao;
    private String nomeTitular;
    private LocalDate vencimentoCartao;
    private String codigoSeguranca;
    private TipoCartao tipoCartao;
    private String idContaBanco;
    private Boolean dependente;
    private UUID usuarioId;
}
