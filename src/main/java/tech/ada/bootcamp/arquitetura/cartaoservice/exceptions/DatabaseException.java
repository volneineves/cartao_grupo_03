package tech.ada.bootcamp.arquitetura.cartaoservice.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseException extends DataIntegrityViolationException {

    public DatabaseException(String msg) {
        super(msg);
    }
}