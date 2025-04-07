package com.example.concorrente_lab10.exceptions.ProdutoExceptions;

import com.example.concorrente_lab10.exceptions.ProgramException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ProdutoIdJaExiste extends ProgramException {

    public ProdutoIdJaExiste() {
        super("Produto com ID já existente.");
    }
}
