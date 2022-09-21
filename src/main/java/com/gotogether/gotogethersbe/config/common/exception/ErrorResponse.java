package com.gotogether.gotogethersbe.config.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(String errorMessage, int statusCode) {
        return ResponseEntity
                .status(statusCode)
                .body(ErrorResponse.builder()
                        .status(statusCode)
                        .message(errorMessage)
                        .build()
                );
    }
}
