package no.ntnu.webshop.group12.webshop.exception;

import java.time.LocalDateTime;

public class APIerror {
    
    LocalDateTime timestamp;
    String message;

    public APIerror(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

}
