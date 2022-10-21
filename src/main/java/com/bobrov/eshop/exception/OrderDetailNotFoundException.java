package com.bobrov.eshop.exception;

public class OrderDetailNotFoundException extends RuntimeException {
    public OrderDetailNotFoundException() {
        super();
    }

    public OrderDetailNotFoundException(String message) {
        super(message);
    }

    public OrderDetailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDetailNotFoundException(Throwable cause) {
        super(cause);
    }
}
