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
public class ProdutoGetDto {

    private String id;

    private String name;

    private Double price;

    private Integer quantity;

    public ProdutoGetDto(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
        this.price = produto.getPrice();
        this.quantity = produto.getQuantity().get();
    }
}
