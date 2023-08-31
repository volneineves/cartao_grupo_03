package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroUsuarioRequest(

        @NotNull
        String nome,

        @Size(max = 100)
        @NotNull
        @NotEmpty
        @Email
        String email,

        @NotNull
        LocalDate aniversario,

        @Valid
        @NotNull
        EnderecoRequest endereco
) {
}
