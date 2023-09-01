package tech.ada.bootcamp.arquitetura.cartaoservice.components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.FaturaService;

@Component
public class FaturaCronJob {

    private final FaturaService faturaService;

    public FaturaCronJob(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void createMonthInvoices() {
        faturaService.createInvoices();
        // TODO corrigir criação quando já existe fatura do mês
    }
}
