package com.esib.esib.service.exceptions;

import java.util.logging.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Meldo Maunze
 */
@ResponseStatus(value = NOT_FOUND)
public class DataBindingViolationException extends DataIntegrityViolationException {


    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(DataBindingViolationException.class.getName());
    /**
     *
     * @param message
     */
    public DataBindingViolationException(String message) {
        super(message);
    }

}