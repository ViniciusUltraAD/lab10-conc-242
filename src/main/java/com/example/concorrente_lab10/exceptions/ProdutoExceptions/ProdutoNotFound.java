package com.example.concorrente_lab10.exceptions.ProdutoExceptions;

import com.example.concorrente_lab10.exceptions.ProgramException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProdutoNotFound extends ProgramException {

    public ProdutoNotFound() {
        super("Produto não encontrado");
    }

}
