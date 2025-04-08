package com.example.concorrente_lab10.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a estrutura de erro personalizada retornada pela API.
 *
 * Contém informações como timestamp, mensagem geral e lista de erros específicos,
 * permitindo respostas mais descritivas e estruturadas em casos de exceções.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorType {

    /**
     * Data e hora em que o erro ocorreu.
     */
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    /**
     * Mensagem geral descritiva sobre o erro ocorrido.
     */
    @JsonProperty("message")
    private String message;

    /**
     * Lista de mensagens de erro específicas, como falhas de validação de campos.
     */
    @JsonProperty("errors")
    private List<String> errors;

    /**
     * Construtor que cria um {@code CustomErrorType} a partir de uma exceção do tipo {@code ProgramException}.
     *
     * @param e Exceção personalizada do sistema que será usada para construir a resposta de erro.
     */
    public CustomErrorType(ProgramException e) {
        this.timestamp = LocalDateTime.now();
        this.message = e.getMessage();
        this.errors = new ArrayList<>();
    }
}