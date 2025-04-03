package com.example.concorrente_lab10.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoGetDto {

    private String id;

    private String name;

    private Double price;

    private Integer quantity;

}
