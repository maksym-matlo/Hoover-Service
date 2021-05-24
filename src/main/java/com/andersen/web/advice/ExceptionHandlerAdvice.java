package com.andersen.web.advice;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * <p> ExceptionHandlerAdvice is a class for holding exceptions <p>
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Constant String describes log message about exception event
     */
    private static final String EXCEPTION_LOG = "{} has occurred during processing request {}";

    /**
     * Constant String describes response message about NullPointerException
     */
    private static final String NULL_POINTER = "Null value was found in the body of the request";

    /**
     * Methods handles NullPointerException
     * @param ex exception thrown out
     * @param request HttpServletRequest instance
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNotNullException(Throwable ex, HttpServletRequest request) {
        logException(ex, request);
        return errorResponse(INTERNAL_SERVER_ERROR, NULL_POINTER);
    }

    /**
     * Methods handles DataIntegrityViolationException
     * @param ex exception thrown out
     * @param request HttpServletRequest instance
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(Throwable ex, HttpServletRequest request) {
        logException(ex, request);
        return errorResponse(INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    /**
     * Methods creating ResponseEntity for exception event
     * @param status HttpStatus instance
     * @param message message fro response
     * @return
     */
    private ResponseEntity<ErrorResponse> errorResponse(HttpStatus status, String message) {
        return new ResponseEntity<>(new ErrorResponse(status, message), status);
    }

    /**
     * Methods creating log messages about exception event
     * @param ex exception thrown out
     * @param request HttpServletRequest instance
     * @return
     */
    private void logException(Throwable ex, HttpServletRequest request) {
        String errorMess = ex.getClass().getSimpleName() + ": " + ex.getCause();
        errorMess += ex.getStackTrace()[0].toString();

        log.error(EXCEPTION_LOG, errorMess, request.getMethod() + " " + request.getRequestURI());
        log.error(Marker.ANY_NON_NULL_MARKER, ex);
    }

    /**
     * Custom Error Response
     */
    @Getter
    private static class ErrorResponse {
        public String timestamp;
        private String status;
        private int error;
        private String message;

        ErrorResponse(HttpStatus httpStatus, String message) {
            this.timestamp = LocalDateTime.now().format(ISO_DATE_TIME);
            this.error = httpStatus.value();
            this.status = httpStatus.getReasonPhrase();
            this.message = message;
        }
    }
}
