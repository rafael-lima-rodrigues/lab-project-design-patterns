package com.labproject.patterns.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer not found")
public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(Long id) {
        super("Customer not found with ID: " + id);
    }
}
