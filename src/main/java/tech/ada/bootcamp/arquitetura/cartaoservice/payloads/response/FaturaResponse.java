package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FaturaResponse(UUID id,

                             LocalDate dataVencimento,

                             LocalDate dataProcessamento,

                             BigDecimal valor,

                             BigDecimal valorPago,
                             List<CompraResponse> resumoCompra) {

}
