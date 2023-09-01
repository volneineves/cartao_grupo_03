package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CartaoResponse {
    private String numeroCartao;
    private String nomeTitular;
    private LocalDate vencimentoCartao;
    private String codigoSeguranca;
    private TipoCartao tipoCartao;
    private String idContaBanco;
    private Boolean dependente;
    private Usuario usuario;
}
