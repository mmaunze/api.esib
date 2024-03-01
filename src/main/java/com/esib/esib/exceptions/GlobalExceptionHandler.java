package com.esib.esib.exceptions;

import com.esib.esib.service.exceptions.AuthorizationException;
import com.esib.esib.service.exceptions.DataBindingViolationException;
import com.esib.esib.service.exceptions.ObjectNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import static org.apache.commons.lang3.exception.ExceptionUtils.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Meldo Maunze
 */
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements AuthenticationFailureHandler {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class.getName());

    /**
     *
     */
    @Value("${server.error.include-exception}")
    private boolean printStackTrace;

    /**
     *
     * @param methodArgumentNotValidException
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details.");
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return unprocessableEntity().body(errorResponse);
    }

    /**
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(
            Exception exception,
            WebRequest request) {
        final var errorMessage = "Unknown error occurred";
        log.error(errorMessage, exception);
        return buildErrorResponse(exception,
                errorMessage, INTERNAL_SERVER_ERROR,
                request);
    }

    /**
     *
     * @param dataIntegrityViolationException
     * @param request
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(CONFLICT)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException dataIntegrityViolationException,
            WebRequest request) {
        var errorMessage = dataIntegrityViolationException.getMostSpecificCause().getMessage();
        log.error("Failed to save entity with integrity problems: " + errorMessage, dataIntegrityViolationException);
        return buildErrorResponse(dataIntegrityViolationException,
                errorMessage, CONFLICT,
                request);
    }

    /**
     *
     * @param constraintViolationException
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException constraintViolationException,
            WebRequest request) {
        log.error("Failed to validate element", constraintViolationException);
        return buildErrorResponse(constraintViolationException, UNPROCESSABLE_ENTITY,
                request);
    }

    /**
     *
     * @param objectNotFoundException
     * @param request
     * @return
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Object> handleObjectNotFoundException(
            ObjectNotFoundException objectNotFoundException,
            WebRequest request) {
        log.error("Failed to find the requested element", objectNotFoundException);
        return buildErrorResponse(objectNotFoundException, NOT_FOUND,
                request);
    }

    /**
     *
     * @param dataBindingViolationException
     * @param request
     * @return
     */
    @ExceptionHandler(DataBindingViolationException.class)
    @ResponseStatus(CONFLICT)
    public ResponseEntity<Object> handleDataBindingViolationException(
            DataBindingViolationException dataBindingViolationException,
            WebRequest request) {
        log.error("Failed to save entity with associated data", dataBindingViolationException);
        return buildErrorResponse(dataBindingViolationException, CONFLICT,
                request);
    }

    /**
     *
     * @param authenticationException
     * @param request
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ResponseEntity<Object> handleAuthenticationException(
            AuthenticationException authenticationException,
            WebRequest request) {
        log.error("Authentication error ", authenticationException);
        return buildErrorResponse(authenticationException, UNAUTHORIZED,
                request);
    }

    /**
     *
     * @param accessDeniedException
     * @param request
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(FORBIDDEN)
    public ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException accessDeniedException,
            WebRequest request) {
        log.error("Authorization error ", accessDeniedException);
        return buildErrorResponse(accessDeniedException, FORBIDDEN,
                request);
    }

    /**
     *
     * @param authorizationException
     * @param request
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(FORBIDDEN)
    public ResponseEntity<Object> handleAuthorizationException(
            AuthorizationException authorizationException,
            WebRequest request) {
        log.error("Authorization error ", authorizationException);
        return buildErrorResponse(authorizationException, FORBIDDEN,
                request);
    }

    /**
     *
     * @param exception
     * @param httpStatus
     * @param request
     * @return
     */
    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }

    /**
     *
     * @param exception
     * @param message
     * @param httpStatus
     * @param request
     * @return
     */
    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (this.printStackTrace) {
            errorResponse.setStackTrace(getStackTrace(exception));
        }
        return status(httpStatus).body(errorResponse);
    }

    /**
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        Integer status = UNAUTHORIZED.value();
        response.setStatus(status);
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse(status, "Username or password are invalid");
        response.getWriter().append(errorResponse.toJson());
    }


}
