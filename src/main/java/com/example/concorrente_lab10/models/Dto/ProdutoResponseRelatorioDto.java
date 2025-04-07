package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;

public class ProdutoResponseRelatorioDto {

    private String id;

    private String name;

    private Integer countSold;

    public ProdutoResponseRelatorioDto(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
        this.countSold = produto.getCountSold().intValue();
    }
}
