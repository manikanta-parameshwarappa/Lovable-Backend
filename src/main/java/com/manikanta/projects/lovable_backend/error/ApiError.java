package com.manikanta.projects.lovable_backend.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ApiError(
        HttpStatus status,
        String message,
        Instant timestamp,
        @JsonInclude(JsonInclude.Include.NON_NULL) List<ApiFieldError> errors
) {
    public ApiError(HttpStatus status, String message) {
        this(status, message, Instant.now(), null);
    }

    public ApiError(HttpStatus status, String message, List<ApiFieldError> errors) {
        this(status, message, Instant.now(), errors);
    }
}
record ApiFieldError(String field, String message){}

// @JsonInclude(JsonInclude.Include.NON_NULL) -> include the errors only when its not null  - Jackson Library !