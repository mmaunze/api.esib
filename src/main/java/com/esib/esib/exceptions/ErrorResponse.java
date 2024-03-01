package com.esib.esib.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
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
    private static final Logger logger = Logger.getLogger(ErrorResponse.class.getName());

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

}
