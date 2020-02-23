package com.assignment.delivery.exception;

public final class DeliveryNotFoundException extends RuntimeException {

    private String id;

    public DeliveryNotFoundException() {
    }

    public DeliveryNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DeliveryNotFoundException(final String message) {
        super(message);
    }

    public DeliveryNotFoundException(String id, final String message) {
        super(message);
        this.id = id;
    }

    public DeliveryNotFoundException(final Throwable cause) {
        super(cause);
    }
}
