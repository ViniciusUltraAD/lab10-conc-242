package com.example.concorrente_lab10.exceptions.ProdutoExceptions;

import com.example.concorrente_lab10.exceptions.ProgramException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um produto solicitado não é encontrado no sistema.
 *
 * <p>Esta exceção resulta em um código HTTP 404 (NOT FOUND), indicando que o recurso não existe.</p>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProdutoNotFound extends ProgramException {

    /**
     * Cria uma nova instância da exceção com a mensagem padrão:
     * "Produto não encontrado".
     */
    public ProdutoNotFound() {
        super("Produto não encontrado");
    }

}
