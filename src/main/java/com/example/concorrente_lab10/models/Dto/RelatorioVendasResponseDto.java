package com.example.concorrente_lab10.models.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado como resposta para o relatório de vendas.
 *
 * Contém o total de vendas realizadas e uma lista de produtos com suas respectivas quantidades vendidas.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioVendasResponseDto {

    /**
     * Quantidade total de vendas realizadas.
     */
    private Integer totalSales;

    /**
     * Lista de produtos com informações resumidas de vendas.
     */
    private List<ProdutoResponseRelatorioDto> products;

    /**
     * Construtor que inicializa os campos com os dados fornecidos.
     *
     * @param totalSales Quantidade total de vendas.
     * @param products Lista de produtos com dados consolidados de vendas.
     */
    public RelatorioVendasResponseDto(int totalSales, List<ProdutoResponseRelatorioDto> products) {
        this.totalSales = totalSales;
        this.products = products;        
    }
}
