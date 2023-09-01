package tech.ada.bootcamp.arquitetura.cartaoservice.strategy;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;

import java.math.BigDecimal;

public interface PaymentStrategy {
  Compra pay(CompraRequest compraRequest);
}
