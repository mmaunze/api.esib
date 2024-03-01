package com.esib.esib.exceptions;

import java.util.logging.Logger;

/**
 *
 * @author Meldo Maunze
 */
public class PreexistingEntityException extends Exception {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(PreexistingEntityException.class.getName());

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

}
