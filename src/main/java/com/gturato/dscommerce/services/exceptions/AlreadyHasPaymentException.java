package com.gturato.dscommerce.services.exceptions;

public class AlreadyHasPaymentException extends RuntimeException {
    public AlreadyHasPaymentException(String message) {
        super(message);
    }
}
