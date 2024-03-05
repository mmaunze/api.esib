package com.esib.esib.exceptions;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;
import static java.util.Objects.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.logging.Logger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Meldo Maunze
 */
@Getter
@Setter
@RequiredArgsConstructor

@JsonInclude(NON_NULL)
public class ErrorResponse {

    /**
     *
     */
    private final int status;

    /**
     *
     */
    private final String message;

    /**
     *
     */
    private String stackTrace;

    /**
     *
     */
    private List<ValidationError> errors;

    /**
     *
     * @param field
     * @param message
     */
    public void addValidationError(String field, String message) {
        if (isNull(errors)) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(new ValidationError(field, message));
    }

    /**
     *
     * @return
     */
    public String toJson() {
        return "{\"status\": " + getStatus() + ", "
                + "\"message\": \"" + getMessage() + "\"}";
    }

    /**
     *
     */
    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError {

        /**
         *
         */
        private final String field;

        /**
         *
         */
        private final String message;
    }

    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(ErrorResponse.class.getName());

}
