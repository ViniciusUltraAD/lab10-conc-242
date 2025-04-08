package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de resposta para a operação de compra de um produto.
 *
 * Contém uma mensagem de confirmação e os dados do produto após a compra.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseCompraDto {

    /**
     * Mensagem indicando o sucesso da operação de compra.
     */
    private String message;

    /**
     * Dados do produto após a compra (incluindo quantidade atualizada).
     */
    private ProdutoResponsePurchase produtoResponsePurchase;

    /**
     * Construtor que gera automaticamente a resposta a partir de um objeto {@link Produto}.
     *
     * @param produto O produto que foi comprado.
     */
    public ProdutoResponseCompraDto(Produto produto) {
        this.message = "Compra realizada com sucesso.";
        this.produtoResponsePurchase = new ProdutoResponsePurchase(produto);
    }
}
