package br.com.green.greenpm.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.green.greenpm.exception.AuthException;
import br.com.green.greenpm.exception.EntityExistsException;
import br.com.green.greenpm.exception.EntityNotFoundException;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GreenPmAPIErrorHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(AuthException.class)
    protected ResponseEntity<Object> handleInvalidAuth(AuthException ex) {
        GreenPmAPIError apiError = new GreenPmAPIError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return this.buildResponseEntity(apiError);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        GreenPmAPIError apiError = new GreenPmAPIError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return this.buildResponseEntity(apiError);
    }
    
    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(EntityExistsException ex) {
        GreenPmAPIError apiError = new GreenPmAPIError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return this.buildResponseEntity(apiError);
    }
    
    private ResponseEntity<Object> buildResponseEntity(GreenPmAPIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}