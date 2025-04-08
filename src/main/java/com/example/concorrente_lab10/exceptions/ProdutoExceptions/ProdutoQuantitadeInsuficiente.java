package com.example.concorrente_lab10.exceptions.ProdutoExceptions;

import com.example.concorrente_lab10.exceptions.ProgramException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando se tenta comprar uma quantidade maior do que a disponível em estoque.
 *
 * Retorna um código HTTP 400 (BAD REQUEST), indicando que a solicitação do cliente é inválida
 * devido à indisponibilidade de estoque.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProdutoQuantitadeInsuficiente extends ProgramException {

    /**
     * Cria uma nova instância da exceção com uma mensagem personalizada informando
     * a quantidade disponível em estoque.
     *
     * @param quantidade Quantidade de produtos disponível em estoque.
     */
    public ProdutoQuantitadeInsuficiente(int quantidade) {

        super("Estoque insuficiente. Quantidade disponível: " + quantidade);
    }
}
