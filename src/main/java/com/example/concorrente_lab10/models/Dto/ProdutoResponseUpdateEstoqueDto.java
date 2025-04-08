package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado como resposta após a atualização do estoque de um produto.
 *
 * <p>Contém uma mensagem de status e a quantidade restante no estoque.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseUpdateEstoqueDto {

    /**
     * Mensagem indicando o status da operação.
     */
    private String message;

    /**
     * Quantidade de produto restante no estoque após a atualização.
     */
    private Integer remainingStock;

    /**
     * Construtor que inicializa o DTO com base nos dados de um {@link Produto}.
     *
     * @param produto O produto atualizado, de onde será obtido o estoque atual.
     */
    public ProdutoResponseUpdateEstoqueDto(Produto produto) {
        this.message = "Estoque atualizado.";
        this.remainingStock = produto.getQuantity().intValue();
    }
}
