package io.github.lucasfreitasrocha.cv_online_backend.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class HandlerError {

    private CustomExceptionDomain customExceptionDomain;
    public HandlerError init(){
        customExceptionDomain = new CustomExceptionDomain();
        return this;
    }

    public HandlerError addError(String message){
        this.customExceptionDomain.getErrors().add(new ContentError("error:", message));
        return this;
    }
    public HandlerError addFieldError(String field, String message){
        this.customExceptionDomain.getErrors().add(new ContentError(field, message));
        return this;
    }
    public HandlerError addHttpStatus(HttpStatus status){
        this.customExceptionDomain.setStatus(status);
        return this;
    }
    public void handle(){
        throw new CustomException(this.customExceptionDomain);
    }
}