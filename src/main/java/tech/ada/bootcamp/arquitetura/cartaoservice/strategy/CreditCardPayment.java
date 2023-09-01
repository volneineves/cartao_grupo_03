package tech.ada.bootcamp.arquitetura.cartaoservice.strategy;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.StatusCompra;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditCardPayment implements PaymentStrategy {
  @Override
  public Compra pay(CompraRequest compraRequest) {

    boolean compraAprovada = verificarComBanco(compraRequest.getIdCartao(), compraRequest.getValor());

    StatusCompra statusCompra = compraAprovada ? StatusCompra.FINALIZADA : StatusCompra.REPROVADA;

    Compra compra = new Compra(compraRequest);
    compra.setStatusCompra(statusCompra);

    return compra;
  }

  private boolean verificarComBanco(UUID idCartao, BigDecimal valor) {
    System.out.println("Compra com cartão de crédito realizada com sucesso!");
    return true;
  }
}
