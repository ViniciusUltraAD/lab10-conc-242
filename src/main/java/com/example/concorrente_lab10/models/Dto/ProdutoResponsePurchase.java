package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para representar os dados principais de um produto após a realização de uma compra.
 *
 * <p>Contém o identificador, nome e a quantidade restante em estoque.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponsePurchase {

    /**
     * Identificador único do produto.
     */
    private String id;

    /**
     * Nome do produto.
     */
    private String name;

    /**
     * Quantidade disponível no estoque após a compra.
     */
    private Integer quantity;

    /**
     * Construtor que inicializa o DTO a partir de um objeto {@link Produto}.
     *
     * @param produto O produto comprado, com seus dados atualizados.
     */
    public ProdutoResponsePurchase(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
        this.quantity = produto.getQuantity().intValue();
    }
}
