package com.example.ReactSpringCollaborationProject.exception;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandle(CustomException e) {

        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @Getter
    @RequiredArgsConstructor
    private static class ErrorResponse {

        private final int httpStatus;
        private final String errorCode;
        private final String message;

        public static ResponseEntity toResponseEntity(ErrorCode errorCode) {
            return ResponseEntity
                    .status(errorCode.getHttpStatus())
                    .body(new ErrorResponse(errorCode.getHttpStatus(), errorCode.getErrorCode(), errorCode.getMessage()));

        }
    }
}