package br.com.green.greenpm.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.green.greenpm.utils.Constants;


/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
public class GreenPmAPIError {
    
    private HttpStatus status;
    
    private String message;
    
    private String debugMessage;
    
    @JsonFormat(shape = Shape.STRING, pattern = Constants.DAT_TIM_FMT)
    private LocalDateTime timestamp;
    
    public GreenPmAPIError() {}

    public GreenPmAPIError(HttpStatus status) {
        super();
        this.status = status;
    }

    public GreenPmAPIError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public GreenPmAPIError(HttpStatus status, String message, String debugMessage) {
        super();
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}