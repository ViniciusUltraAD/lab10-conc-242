package com.example.concorrente_lab10.exceptions.ProdutoExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ProdutoIdJaExiste extends RuntimeException {

    public ProdutoIdJaExiste() {
        super("Produto com ID já existente.");
    }
}
