package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false, mappedBy = "usuario")
    @JoinColumn(name = "id_usuario")
    private Endereco endereco;

}
