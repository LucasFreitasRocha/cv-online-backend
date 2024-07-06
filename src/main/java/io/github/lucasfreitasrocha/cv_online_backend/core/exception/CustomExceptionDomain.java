package io.github.lucasfreitasrocha.cv_online_backend.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomExceptionDomain {
    private List<ContentError> errors;
    private HttpStatus status;
    private LocalDateTime date;
    private String path;


    public CustomExceptionDomain() {
        this.status = HttpStatus.BAD_REQUEST;
        this.date = LocalDateTime.now();
        this.errors = new ArrayList<>();
    }

    public CustomExceptionDomain(List<ContentError> errors, HttpStatus status, LocalDateTime timestamp) {
        this.errors = errors;
        this.status = status;
        this.date = timestamp;
    }


}
