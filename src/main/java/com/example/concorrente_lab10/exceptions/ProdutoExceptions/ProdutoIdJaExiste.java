package com.example.concorrente_lab10.exceptions.ProdutoExceptions;

import com.example.concorrente_lab10.exceptions.ProgramException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando se tenta cadastrar um produto com um ID que já está em uso.
 *
 * Esta exceção retorna um código HTTP 409 (CONFLICT) para indicar o conflito de dados.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ProdutoIdJaExiste extends ProgramException {

    /**
     * Cria uma nova instância da exceção com a mensagem padrão:
     * "Produto com ID já existente."
     */
    public ProdutoIdJaExiste() {
        super("Produto com ID já existente.");
    }
}
