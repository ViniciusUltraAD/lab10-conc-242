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
public class ProdutoResponsePurchase {

    private String id;

    private String name;

    private Integer quantity;

    public ProdutoResponsePurchase(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
        this.quantity = produto.getQuantity().intValue();
    }
}
