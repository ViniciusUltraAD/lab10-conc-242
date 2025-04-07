package com.example.concorrente_lab10.exceptions;

/**
 * Classe base para todas as exceções personalizadas da aplicação.
 *
 * <p>Estende {@link RuntimeException} e permite definir mensagens específicas
 * para os erros de negócio tratados no sistema.</p>
 *
 * <p>Utilizada em conjunto com o {@link com.example.concorrente_lab10.exceptions.GlobalExceptionHandler}
 * para retornar respostas padronizadas ao cliente.</p>
 */
public class ProgramException extends RuntimeException {

    /**
     * Construtor padrão que define uma mensagem genérica de erro.
     */
    public ProgramException() {
        super("Ocorreu um erro.");
    }

    /**
     * Construtor que permite definir uma mensagem personalizada para a exceção.
     *
     * @param message Mensagem descritiva do erro.
     */
    public ProgramException(String message) {
        super(message);
    }
}
