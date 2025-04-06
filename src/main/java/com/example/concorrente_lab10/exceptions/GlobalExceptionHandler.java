package com.example.concorrente_lab10.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private CustomErrorType defaultCustomErrorType(String message) {
        return CustomErrorType.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .errors(new ArrayList<>())
                .build();
    }

    //Handler padrao
    @ExceptionHandler(ProgramException.class)
    @ResponseBody
    public ResponseEntity<CustomErrorType> handleProgramException(ProgramException ex) {
        ResponseStatus status = ex.getClass().getAnnotation(ResponseStatus.class);
        return ResponseEntity.status(status.value()).body(defaultCustomErrorType(ex.getMessage()));
    }
}