package com.example.concorrente_lab10.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPostDto {

    private String id;

    private String name;

    private Double price;

    private Integer quantity;
}
