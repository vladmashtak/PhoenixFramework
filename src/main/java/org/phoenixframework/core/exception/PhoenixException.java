package org.phoenixframework.core.exception;

/**
 * @author Oleg Marchenko
 */

public class PhoenixException extends RuntimeException {

    public PhoenixException(String message) {
        super(message);
    }

    public PhoenixException(final Throwable cause) {
        super(cause);
    }

    public PhoenixException(String message, Throwable cause) {
        super(message, cause);
    }
}
