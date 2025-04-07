package com.example.concorrente_lab10.exceptions.ProdutoExceptions;

import com.example.concorrente_lab10.exceptions.ProgramException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProdutoQuantitadeInsuficiente extends ProgramException {

    public ProdutoQuantitadeInsuficiente(int quantidade) {

        super("Estoque insuficiente. Quantidade disponível: " + quantidade);
    }
}
