package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
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

    @OneToOne(optional = false)
    private Usuario usuario;

}
