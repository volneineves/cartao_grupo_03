package tech.ada.bootcamp.arquitetura.cartaoservice.components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.InvoiceService;

@Component
public class InvoiceCronJob {

    private final InvoiceService invoiceService;

    public InvoiceCronJob(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void createMonthInvoices() {
        invoiceService.createInvoices();
        // TODO corrigir criação quando já existe fatura do mês
    }
}
