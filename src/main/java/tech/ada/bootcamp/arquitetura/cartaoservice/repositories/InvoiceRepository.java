package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Invoice;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.TotalPurchasesProjection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    @Query("SELECT new tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.TotalPurchasesProjection(c.card.id, SUM(c.amount)) " +
            "FROM Purchase c " +
            "WHERE c.purchaseDate BETWEEN :beginDate AND :endDate " +
            "GROUP BY c.card.id")
    List<TotalPurchasesProjection> findTotalPurchasesByDate(LocalDateTime beginDate, LocalDateTime endDate);

    @Query("SELECT f FROM Invoice f WHERE f.card.user.id = :idUser")
    List<Invoice> findAllByUserId(UUID idUser);
}