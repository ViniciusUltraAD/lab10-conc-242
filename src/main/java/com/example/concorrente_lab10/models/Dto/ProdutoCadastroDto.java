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
public class ProdutoCadastroDto {

    private String id;

    private String name;

    public ProdutoCadastroDto(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
    }
}
