package com.example.concorrente_lab10.models.Dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPutDto {

    @Positive(message = "Quantidade Tem que ser positiva")
    private Integer quantity;

}
