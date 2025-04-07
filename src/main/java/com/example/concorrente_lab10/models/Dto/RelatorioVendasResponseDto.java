package com.example.concorrente_lab10.models.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioVendasResponseDto {

    private Integer totalSales;

    private List<ProdutoResponseRelatorioDto> products;

    public RelatorioVendasResponseDto(int totalSales, List<ProdutoResponseRelatorioDto> products) {
        this.totalSales = totalSales;
        this.products = products;        
    }
}
