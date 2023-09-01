package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, UUID> {
    List<Cartao> findCartaoByUsuarioId(UUID id);
    Optional<Cartao> findCartaoByNumeroCartao(String numeroCartao);

}