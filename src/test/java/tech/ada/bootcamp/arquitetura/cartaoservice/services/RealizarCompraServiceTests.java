package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.StatusCompra;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;

import java.math.BigDecimal;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class RealizarCompraServiceTests {

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private RealizarCompraService realizarCompraService;

    @Test
    @DisplayName("Should return CompraResponse with success status.")
    void shouldSaveSuccessfullyNewPuchased(){
        CompraRequest compraRequest = Mockito.mock(CompraRequest.class);

        UUID randomCardId = UUID.randomUUID();
        String loja = "Venda do Zé";
        BigDecimal compraValor = new BigDecimal("15.00");

        // Given
        Mockito.when(compraRequest.getIdCartao()).thenReturn(randomCardId);
        Mockito.when(compraRequest.getLoja()).thenReturn(loja);
        Mockito.when(compraRequest.getValor()).thenReturn(compraValor);

        CompraResponse successCompraResponse = new CompraResponse(randomCardId, loja, compraValor, StatusCompra.FINALIZADA);

        // Act - when
        CompraResponse compraResponse = realizarCompraService.execute(compraRequest);

        // Assert
        Assertions.assertEquals(successCompraResponse ,compraResponse);
    }

//    @Test
//    void shouldThrowErrorWhenCardNumberNotFoundInDB() {
//
//        CompraRequest compraRequest = Mockito.mock(CompraRequest.class);
//
//        // Given
//        given(compraRequest.getIdCartao())
//
//        // Act
//
//        // Verify
//
//    }

}
