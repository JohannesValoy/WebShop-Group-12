package no.ntnu.webshop.group12.webshop.exception;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import javax.security.sasl.AuthenticationException;
@ControllerAdvice(basePackages = {"no.ntnu.webshop.group12.webshop.controllers.api"} )
public class APIControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(getResponseObject(ex, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AccessDeniedException.class, ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        APIError apiError = new APIError("You are not authorized to access this resource");
        return new ResponseEntity<>(
                apiError.getErrorAttributes(request), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({Unauthorized.class, AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleUnauthorizedException(
            Exception ex, WebRequest request) {
        APIError apiError = new APIError("You are not authorized to access this resource");
        return new ResponseEntity<>(
                apiError.getErrorAttributes(request), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleIllegalArgumentException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(getResponseObject(ex.getMessage(), request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConversionFailedException(
            Exception ex, WebRequest request) {
        String message = ex.getMessage();
        if (ex.getMessage().contains("Failed to convert from type")) {
            message = "Invalid input, please check your input and try again.";
        }
        return new ResponseEntity<>(getResponseObject(message, request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConstraintException(ConstraintViolationException ex, WebRequest request) {
        String message = ex.getConstraintViolations().stream()
                        .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                        .collect(Collectors.toList()).toString();
        return new ResponseEntity<>(getResponseObject(message, request), HttpStatus.BAD_REQUEST);
    }

    private Object getResponseObject(String message, WebRequest request) {
        return new APIError(message).getErrorAttributes(request);
    }

    private Object getResponseObject(Exception ex, WebRequest request) {
        return getResponseObject(ex.getMessage(), request);
    }
}