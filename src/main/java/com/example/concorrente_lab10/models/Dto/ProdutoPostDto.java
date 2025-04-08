package com.example.concorrente_lab10.models.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para o cadastro de um novo produto.
 *
 * <p>Inclui os campos obrigatórios para a criação: ID, nome, preço e quantidade.
 * Também possui anotações de validação para garantir a integridade dos dados enviados pelo cliente.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPostDto {

    /**
     * Identificador do produto.
     * <p>Não pode ser nulo e deve ser um número positivo.</p>
     */
    @NotBlank(message = "ID Não pode Ser Nulo")
    @Pattern(regexp = "^[1-9]\\d*$", message = "Deve ser um número positivo")
    private String id;

    /**
     * Nome do produto.
     * <p>Não pode ser nulo ou vazio.</p>
     */
    @NotBlank(message = "Nome Não pode ser nulo")
    private String name;

    /**
     * Preço do produto.
     * <p>Deve ser um número positivo.</p>
     */
    @Positive(message = "Preço Tem que ser positivo")
    private Double price;

    /**
     * Quantidade inicial do produto no estoque.
     * <p>Deve ser um número inteiro positivo.</p>
     */
    @Positive(message = "Quantidade Tem que ser positiva")
    private Integer quantity;
}
