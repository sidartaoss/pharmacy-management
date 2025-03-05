package com.pharmacy.management.domain.exceptions;

public class NotFoundException extends RuntimeException {

    protected NotFoundException(final String aMessage, final Throwable t) {
        super(aMessage, t);
    }

    public static NotFoundException with(final String message) {
        return new NotFoundException(message, null);
    }
}
