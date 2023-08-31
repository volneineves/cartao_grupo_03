package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    private LocalDate aniversario;

    @CreationTimestamp
    @Column(nullable = false)
    private Date criadoEm;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date atualizadoEm;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Usuario(CadastroUsuarioRequest request) {
        this.nome = request.nome();
        this.email = request.email();
        this.aniversario = request.aniversario();
        this.endereco = new Endereco(request.endereco());
    }

    public CadastroUsuarioResponse toResponse() {
        return new CadastroUsuarioResponse(id, nome, email, aniversario, endereco.toResponse(), criadoEm, atualizadoEm);
    }
}
