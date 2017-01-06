package org.dronezone.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception Handler, any exception thrown out of a Controller will be handled here,
 * With the most specific handler getting the exception.
 *
 * @author Nicholas Drone
 * @since 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    public static final Logger log = LoggerFactory
        .getLogger(GlobalExceptionHandler.class);

    /**
     * Catch all exception handler to return an error 500. You should really fix these
     * exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e)
    {
        log.error(" Unexpected Exception caught in GlobalExceptionHandler", e);
        return new ResponseEntity(ErrorResponse
            .of(HttpStatus.INTERNAL_SERVER_ERROR, "servererror", e.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
}