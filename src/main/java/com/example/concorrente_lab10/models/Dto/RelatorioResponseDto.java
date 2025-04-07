package com.example.concorrente_lab10.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioResponseDto {

    private Integer totalSale;

    private List<ProdutoResponseRelatorioDto> produtos;
}
