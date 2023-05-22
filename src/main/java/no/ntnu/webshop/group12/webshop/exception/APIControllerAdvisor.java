package no.ntnu.webshop.group12.webshop.exception;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import javax.security.sasl.AuthenticationException;

@ControllerAdvice(basePackages = "no.ntnu.webshop.group12.webshop.controllers.API")
public class APIControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<APIerror> handleNotFoundException(
            NotFoundException ex, WebRequest request) {
        APIerror apiError = new APIerror(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AccessDeniedException.class, ForbiddenException.class, Unauthorized.class, AuthenticationException.class})
    protected ResponseEntity<APIerror> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        APIerror apiError = new APIerror("You are not authorized to access this resource"); 
        return new ResponseEntity<>(
                apiError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<APIerror> handleIllegalArgumentException(
            Exception ex, WebRequest request) {
        APIerror apiError = new APIerror(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    protected ResponseEntity<APIerror> handleConversionFailedException(
            Exception ex, WebRequest request) {
        String message = ex.getMessage();
        if (ex.getMessage().contains("Failed to convert from type")) {
            message = "Invalid input, please check your input and try again.";
        }
        APIerror apiError = new APIerror(message);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<APIerror> handleConstraintException(ConstraintViolationException ex, WebRequest request) {
        String message = ex.getConstraintViolations().stream()
                        .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                        .collect(Collectors.toList()).toString();
        APIerror apiError = new APIerror(message);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}