package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.TotalComprasProjection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, UUID> {

    @Query("SELECT new tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.TotalComprasProjection(c.cartao.id, SUM(c.valor)) " +
            "FROM Compra c " +
            "WHERE c.dataCompra BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY c.cartao.id")
    List<TotalComprasProjection> findTotalComprasByData(LocalDateTime dataInicio, LocalDateTime dataFim);
}