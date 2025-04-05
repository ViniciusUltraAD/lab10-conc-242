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
public class ProdutoResponseUpdateEstoqueDto {

    private String message;

    private Integer remainingStock;

    public ProdutoResponseUpdateEstoqueDto(Produto produto) {
        this.message = "Estoque atualizado.";
        this.remainingStock = produto.getQuantity().intValue();
    }
}
