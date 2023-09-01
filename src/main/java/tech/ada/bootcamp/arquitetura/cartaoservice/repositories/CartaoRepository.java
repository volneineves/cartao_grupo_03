package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;

import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {
    List<Cartao> findCartaoByUsuario_Identificador(String identificador);
    Cartao findCartaoByNumeroCartao(String numeroCartao);

}