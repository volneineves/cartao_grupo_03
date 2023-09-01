package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Card;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Invoice;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.DatabaseException;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.ResourceNotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.InvoiceResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.TotalPurchasesProjection;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.InvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public InvoiceResponse findById(UUID id) {
        Invoice invoice = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice could not be found"));
        return invoice.toResponse();
    }

    public List<InvoiceResponse> findAllByUser(UUID userId){
        return repository.findAllByUserId(userId).stream().map(Invoice::toResponse).toList();
    }

    public void createInvoices() {
        LocalDateTime firstDayOfMonth = getFirstDayOfCurrentMonth();
        LocalDateTime lastDayOfMonth = getLastDayOfCurrentMonth();
        LocalDate invoiceExpirationDate = calculateInvoiceExpirationDate(lastDayOfMonth);
        List<TotalPurchasesProjection> totalPurchasesByCard = repository.findTotalPurchasesByDate(firstDayOfMonth, lastDayOfMonth);
        List<Invoice> invoices = prepareInvoicesList(totalPurchasesByCard, invoiceExpirationDate);
        saveAllOrFail(invoices);
    }

    private LocalDateTime getFirstDayOfCurrentMonth() {
        YearMonth yearMonth = YearMonth.now();
        return yearMonth.atDay(1).atStartOfDay();
    }

    private LocalDateTime getLastDayOfCurrentMonth() {
        YearMonth yearMonth = YearMonth.now();
        return yearMonth.atEndOfMonth().atStartOfDay();
    }

    private LocalDate calculateInvoiceExpirationDate(LocalDateTime lastDayOfMonth) {
        return lastDayOfMonth.plusDays(10).toLocalDate();
    }

    private List<Invoice> prepareInvoicesList(List<TotalPurchasesProjection> totalPurchasesByCard, LocalDate expirationDate) {
        List<Invoice> invoices = new ArrayList<>();
        for (TotalPurchasesProjection item : totalPurchasesByCard) {
            Card card = getCard(item);
            Invoice newInvoice = new Invoice();
            newInvoice.setExpirationDate(expirationDate);
            newInvoice.setAmountPaid(BigDecimal.ZERO);
            newInvoice.setCard(card);
            newInvoice.setAmount(item.totalAmount());
            newInvoice.setProcessingDate(LocalDate.now());
            invoices.add(newInvoice);
        }
        return invoices;
    }

    private static Card getCard(TotalPurchasesProjection item) {
        Card card = new Card();
        card.setId(item.cardId());
        return card;
    }

    private void saveAllOrFail(List<Invoice> invoices) {
        try {
            repository.saveAllAndFlush(invoices);
        } catch (Exception e){
            throw new DatabaseException("Error while saving invoices");
        }
    }
}
