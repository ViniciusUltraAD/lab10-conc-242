package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para retornar os dados de um produto ao cliente.
 *
 * <p>Inclui informações como identificador, nome, preço e quantidade disponível.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoGetDto {

    /**
     * Identificador do produto.
     */
    private String id;

    /**
     * Nome do produto.
     */
    private String name;

    /**
     * Preço unitário do produto.
     */
    private Double price;

    /**
     * Quantidade disponível em estoque.
     */
    private Integer quantity;

    /**
     * Construtor que popula o DTO a partir de uma instância da entidade {@link Produto}.
     *
     * @param produto Objeto {@code Produto} usado como fonte de dados.
     */
    public ProdutoGetDto(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
        this.price = produto.getPrice();
        this.quantity = produto.getQuantity().get();
    }
}
