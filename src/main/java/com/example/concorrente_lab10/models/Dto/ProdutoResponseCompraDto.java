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
public class ProdutoResponseCompraDto {

    private String message;

    private ProdutoResponsePurchase produtoResponsePurchase;

    public ProdutoResponseCompraDto(Produto produto) {
        this.message = "Compra realizada com sucesso.";
        this.produtoResponsePurchase = new ProdutoResponsePurchase(produto);
    }
}
