package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CartaoResponse {
    private UUID id;
    private String numeroCartao;
    private String nomeTitular;
    private LocalDate vencimentoCartao;
    private String codigoSeguranca;
    private TipoCartao tipoCartao;
    private String idContaBanco;
    private Boolean dependente;
    private UUID usuarioId;
}
