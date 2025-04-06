package com.example.concorrente_lab10.exceptions.ProdutoExceptions;

import com.example.concorrente_lab10.exceptions.ProgramException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProdutoQuantitadeInsuficiente extends ProgramException {

    //Essa excecao recebe mensagem pois a quantitade que o usuário quer comprar e a quantidade disponível variam.
    //O exemplo está no documento lab10.
    public ProdutoQuantitadeInsuficiente(String message) {
        super(message);
    }
}
