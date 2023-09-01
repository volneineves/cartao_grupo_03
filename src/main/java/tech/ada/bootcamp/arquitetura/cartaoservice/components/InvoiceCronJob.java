package tech.ada.bootcamp.arquitetura.cartaoservice.components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Card;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CardService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.InvoiceService;

import java.time.LocalDate;
import java.util.List;

@Component
public class InvoiceCronJob {

    private final InvoiceService invoiceService;
    private final CardService cardService;

    public InvoiceCronJob(InvoiceService invoiceService, CardService cardService) {
        this.invoiceService = invoiceService;
        this.cardService = cardService;
    }

    @Scheduled(cron = "0 0 8 * * *") // Executa todos os dias às 08h da manhã
    public void createMonthInvoicesEveryDay() {
        int todayDay = LocalDate.now().getDayOfMonth();
        List<Card> allCards = cardService.findAllByClosingDay(todayDay);
        invoiceService.createInvoicesByCards(allCards);
    }
}
