package com.esib.esib.exceptions;

import java.util.logging.Logger;

/**
 *
 * @author Meldo Maunze
 */
public class NonexistentEntityException extends Exception {

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

    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(NonexistentEntityException.class.getName());

}
