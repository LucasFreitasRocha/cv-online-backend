package io.github.lucasfreitasrocha.cv_online_backend.entrypoint.exception;

import io.github.lucasfreitasrocha.cv_online_backend.core.exception.ContentError;
import io.github.lucasfreitasrocha.cv_online_backend.core.exception.CustomException;
import io.github.lucasfreitasrocha.cv_online_backend.core.exception.CustomExceptionDomain;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler  {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionDomain> handler(CustomException e, HttpServletRequest req) {
        e.getHandlerErrorModel().setPath(req.getRequestURI());
        return ResponseEntity.status(e.getHandlerErrorModel().getStatus()).body(e.getHandlerErrorModel());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionDomain> othersExpections(Exception e, HttpServletRequest req){
        CustomExceptionDomain model = new CustomExceptionDomain();
        model.getErrors().add(new ContentError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()));
        model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        model.setPath(req.getRequestURI());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(model);

    }
}