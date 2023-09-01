package tech.ada.bootcamp.arquitetura.cartaoservice.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.ResponseStandardError;

public interface ExceptionHandlerStrategy {
    boolean supports(Exception exception);
    ResponseEntity<ResponseStandardError> handleException(Exception exception, HttpServletRequest request);
}
