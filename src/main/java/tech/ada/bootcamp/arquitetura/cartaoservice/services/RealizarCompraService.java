package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.StatusCompra;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.FaturaRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RealizarCompraService {

  private final CompraRepository repository;

  public CompraResponse execute(CompraRequest compraRequest) {

    boolean compraAprovada = verificarComBanco(compraRequest.getIdCartao(), compraRequest.getValor());

    StatusCompra statusCompra = compraAprovada ? StatusCompra.FINALIZADA : StatusCompra.REPROVADA;

    Compra compra = new Compra(compraRequest);
    compra.setStatusCompra(statusCompra);

    repository.save(compra);

    return compra.toResponse();
  }

  private boolean verificarComBanco(UUID idCartao, BigDecimal valor) {
    return true;
  }
}
