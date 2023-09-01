package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.DatabaseException;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.ResourceNotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.TotalComprasProjection;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.FaturaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FaturaService {

    private final FaturaRepository repository;

    public FaturaService(FaturaRepository repository) {
        this.repository = repository;
    }

    public FaturaResponse findById(UUID id) {
        Fatura fatura = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fatura could not be found"));
        return fatura.toResponse();
    }

    public void createInvoices() {
        LocalDateTime firstDayOfMonth = getFirstDayOfCurrentMonth();
        LocalDateTime lastDayOfMonth = getLastDayOfCurrentMonth();
        LocalDateTime expireInvoiceDateMonth = calculateInvoiceExpirationDate(lastDayOfMonth);
        List<TotalComprasProjection> totalComprasPorCartao = repository.findTotalComprasByData(firstDayOfMonth, lastDayOfMonth);
        List<Fatura> faturas = prepareFaturasList(totalComprasPorCartao, expireInvoiceDateMonth);
        saveAllOrFail(faturas);
    }

    private LocalDateTime getFirstDayOfCurrentMonth() {
        YearMonth yearMonth = YearMonth.now();
        return yearMonth.atDay(1).atStartOfDay();
    }

    private LocalDateTime getLastDayOfCurrentMonth() {
        YearMonth yearMonth = YearMonth.now();
        return yearMonth.atEndOfMonth().atStartOfDay();
    }

    private LocalDateTime calculateInvoiceExpirationDate(LocalDateTime lastDayOfMonth) {
        return lastDayOfMonth.plusDays(10);
    }

    private List<Fatura> prepareFaturasList(List<TotalComprasProjection> totalComprasPorCartao, LocalDateTime expireInvoiceDateMonth) {
        List<Fatura> faturas = new ArrayList<>();
        for (TotalComprasProjection item : totalComprasPorCartao) {
            Cartao cartao = getCartao(item);
            Fatura novaFatura = new Fatura();
            novaFatura.setDataVencimento(expireInvoiceDateMonth.toLocalDate());
            novaFatura.setValorPago(BigDecimal.ZERO);
            novaFatura.setCartao(cartao);
            novaFatura.setValor(item.valorTotal());
            novaFatura.setDataProcessamento(LocalDate.now());
            faturas.add(novaFatura);
        }
        return faturas;
    }

    private static Cartao getCartao(TotalComprasProjection item) {
        Cartao cartao = new Cartao();
        cartao.setId(item.idCartao());
        return cartao;
    }

    private void saveAllOrFail(List<Fatura> faturas) {
        try {
            repository.saveAllAndFlush(faturas);
        } catch (Exception e){
            throw new DatabaseException("Error while saving faturas");
        }
    }
}
