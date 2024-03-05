package com.esib.esib.exceptions;

import java.util.logging.Logger;

/**
 *
 * @author Meldo Maunze
 */
public class PreexistingEntityException extends Exception {

    /**
     *
     * @param message
     * @param cause
     */
    public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     */
    public PreexistingEntityException(String message) {
        super(message);
    }

    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(PreexistingEntityException.class.getName());

}
