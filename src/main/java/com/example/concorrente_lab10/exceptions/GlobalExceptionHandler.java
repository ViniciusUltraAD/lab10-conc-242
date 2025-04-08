package com.example.concorrente_lab10.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Classe responsável por capturar exceções lançadas pelos controladores da aplicação
 * e retornar respostas estruturadas e consistentes para o cliente.
 *
 * Utiliza o mecanismo do Spring de tratamento global de exceções com {@code @RestControllerAdvice}.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Cria um objeto {@link CustomErrorType} padrão a partir de uma mensagem de erro.
     *
     * @param message Mensagem principal do erro.
     * @return Um {@code CustomErrorType} contendo a mensagem, timestamp atual e lista de erros vazia.
     */
    private CustomErrorType defaultCustomErrorType(String message) {
        return CustomErrorType.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .errors(new ArrayList<>())
                .build();
    }

    /**
     * Manipula exceções do tipo {@link ProgramException} e suas subclasses,
     * retornando um corpo de erro padronizado com o código HTTP definido por {@code @ResponseStatus}.
     *
     * @param ex Exceção capturada.
     * @return {@code ResponseEntity} contendo um {@code CustomErrorType} com detalhes do erro.
     */
    @ExceptionHandler(ProgramException.class)
    @ResponseBody
    public ResponseEntity<CustomErrorType> handleProgramException(ProgramException ex) {
        ResponseStatus status = ex.getClass().getAnnotation(ResponseStatus.class);
        return ResponseEntity.status(status.value()).body(defaultCustomErrorType(ex.getMessage()));
    }
}