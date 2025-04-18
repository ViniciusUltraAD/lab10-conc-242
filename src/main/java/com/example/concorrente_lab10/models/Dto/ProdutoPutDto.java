package com.example.concorrente_lab10.models.Dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para atualizar a quantidade de um produto em estoque.
 *
 * Este DTO é usado nas requisições do tipo PUT, onde apenas a quantidade será modificada.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPutDto {

    /**
     * Nova quantidade a ser atribuída ao estoque do produto.
     * Deve ser um número inteiro positivo.
     */
    @Positive(message = "Quantidade Tem que ser positiva")
    private Integer quantity;

}
