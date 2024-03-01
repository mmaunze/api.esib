package com.esib.esib.service.exceptions;

import java.util.logging.Logger;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Meldo Maunze
 */
@ResponseStatus(value = FORBIDDEN)
public class AuthorizationException extends AccessDeniedException {

    /**
     *
     * @param message
     */
    public AuthorizationException(String message) {
        super(message);
    }
    private static final Logger LOG = Logger.getLogger(AuthorizationException.class.getName());

}