package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.enums.StatusCompra;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.strategy.PaymentStrategy;
import tech.ada.bootcamp.arquitetura.cartaoservice.strategy.PaymentStrategyFactory;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RealizarCompraService {

  private final CompraRepository repository;

  public CompraResponse execute(CompraRequest compraRequest) {

    PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(compraRequest.getMethod());

    Compra compra = paymentStrategy.pay(compraRequest);

    repository.save(compra);

    return compra.toResponse();
  }
}
