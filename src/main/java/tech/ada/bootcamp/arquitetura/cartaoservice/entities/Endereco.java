package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.EnderecoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.EnderecoResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UsuarioService;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;


    private String complemento;

    @Column(nullable = false)
    private String numero;

    public Endereco(EnderecoRequest request) {
        this.cep = request.cep();
        this.rua = request.rua();
        this.bairro = request.bairro();
        this.cidade = request.cidade();
        this.estado = request.estado();
        this.complemento = request.complemento();
        this.numero = request.numero();
    }

    public EnderecoResponse toResponse() {
        return new EnderecoResponse(id, cep, rua, bairro, cidade, estado, complemento, numero);
    }
}
