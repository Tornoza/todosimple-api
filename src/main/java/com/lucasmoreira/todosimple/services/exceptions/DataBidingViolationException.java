package com.lucasmoreira.todosimple.services.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBidingViolationException extends DataIntegrityViolationException {

    public DataBidingViolationException(String message) {
        super(message);
    }

}