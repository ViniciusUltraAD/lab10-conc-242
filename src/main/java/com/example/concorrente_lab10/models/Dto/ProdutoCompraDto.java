package com.example.concorrente_lab10.models.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para representar a solicitação de compra de um produto.
 *
 * Contém os dados necessários para realizar uma operação de compra, com validações para garantir
 * a integridade das informações enviadas.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCompraDto {

    /**
     * Identificador do produto a ser comprado.
     * Não pode ser nulo e deve ser um número positivo.
     */
    @NotBlank(message = "ID Não pode Ser Nulo")
    @Pattern(regexp = "^[1-9]\\d*$", message = "Deve ser um número positivo")
    private String id;

    /**
     * Quantidade do produto a ser comprada.
     * Deve ser um número inteiro positivo.
     */
    @Positive(message = "Quantidade Tem que ser positiva")
    private Integer quantity;
}
