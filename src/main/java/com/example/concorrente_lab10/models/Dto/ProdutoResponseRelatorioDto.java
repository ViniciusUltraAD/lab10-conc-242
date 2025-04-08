package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para representar um produto no relatório de vendas.
 *
 * <p>Inclui o identificador, nome do produto e a quantidade total vendida.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseRelatorioDto {

    /**
     * Identificador único do produto.
     */
    private String id;

    /**
     * Nome do produto.
     */
    private String name;

    /**
     * Quantidade total de unidades vendidas do produto.
     */
    private Integer countSold;

    /**
     * Construtor que inicializa o DTO a partir de um objeto {@link Produto}.
     *
     * @param produto O produto cujos dados serão utilizados no relatório.
     */
    public ProdutoResponseRelatorioDto(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
        this.countSold = produto.getCountSold().intValue();
    }
}
