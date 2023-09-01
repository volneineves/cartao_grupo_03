package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record InvoiceResponse(UUID id,
                              LocalDate processingDate,
                              LocalDate expirationDate,

                              BigDecimal amount,
                              BigDecimal amountPaid,
                              List<ResponsePurchase> purchases) {

}
