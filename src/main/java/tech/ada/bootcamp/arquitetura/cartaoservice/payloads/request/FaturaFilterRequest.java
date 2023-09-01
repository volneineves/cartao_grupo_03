package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import java.time.LocalDate;
import java.util.UUID;

public record FaturaFilterRequest(UUID idUsuario,
                                  LocalDate minDataProcessamento,
                                  LocalDate maxDataProcessamento,
                                  LocalDate dataVencimento) {
}
