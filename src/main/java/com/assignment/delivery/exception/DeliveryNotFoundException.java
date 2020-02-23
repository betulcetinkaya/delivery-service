package com.assignment.delivery.exception;

public final class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(final String message) {
        super(message);
    }
}
