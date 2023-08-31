package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record CadastroUsuarioResponse(
        UUID id,
        String nome,
        String email,
        LocalDate aniversario,
        EnderecoResponse endereco,
        Date criadoEm,
        Date atualizadoEm) {
}
