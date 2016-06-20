package org.dronezone.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Global Exception Handler, any exception thrown out of a Controller will be handled here,
 * With the most specific handler getting the exception.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    public static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Catch all exception handler to return an error 500. You should really fix these
     * exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest req,
            HttpServletResponse resp)
    {
        LOG.error(" Unexpected Exception caught in GlobalExceptionHandler", e);
        return new ResponseEntity<ErrorResponse>(
                ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "servererror", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}