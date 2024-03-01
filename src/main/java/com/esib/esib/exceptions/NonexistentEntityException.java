package com.esib.esib.exceptions;

import java.util.logging.Logger;

/**
 *
 * @author Meldo Maunze
 */
public class NonexistentEntityException extends Exception {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(NonexistentEntityException.class.getName());

    /**
     *
     * @param message
     * @param cause
     */
    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     */
    public NonexistentEntityException(String message) {
        super(message);
    }

}
