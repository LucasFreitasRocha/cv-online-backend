package io.github.lucasfreitasrocha.cv_online_backend.core.exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private  CustomExceptionDomain customExceptionDomain;

    public CustomException(CustomExceptionDomain domain){
        this.customExceptionDomain = domain;
    }
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomExceptionDomain getHandlerErrorModel() {
        return customExceptionDomain;
    }
}
