package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseCadastroDto {

    private String message;
    private ProdutoCadastroDto produtoCadastroDto;

    public ProdutoResponseCadastroDto(Produto produto) {
        this.message = "Produto cadastrado com sucesso.";
        this.produtoCadastroDto = new ProdutoCadastroDto(produto);    }
}
