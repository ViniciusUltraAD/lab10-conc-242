package com.example.concorrente_lab10.models.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCompraDto {

    @NotBlank(message = "ID Não pode Ser Nulo")
    @Pattern(regexp = "^[1-9]\\d*$", message = "Deve ser um número positivo")
    private String id;

    @Positive(message = "Quantidade Tem que ser positiva")
    private Integer quantity;
}
