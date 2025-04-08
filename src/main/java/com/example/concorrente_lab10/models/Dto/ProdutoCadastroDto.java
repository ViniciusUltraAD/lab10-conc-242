package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para o cadastro de novos produtos na aplicação.
 *
 * Contém os campos necessários para a criação de um produto, com validações aplicadas.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCadastroDto {

    /**
     * Identificador do produto.
     * Deve ser um número inteiro positivo, validado por expressão regular.
     */
    @Pattern(regexp = "^[1-9]\\d*$", message = "Deve ser um número positivo")
    private String id;

    /**
     * Nome do produto.
     * Não pode ser nulo ou vazio.
     */
    @NotBlank(message = "O nome não pode Ser nulo")
    private String name;

    /**
     * Construtor que popula o DTO a partir de uma instância da entidade {@link Produto}.
     *
     * @param produto Objeto {@code Produto} a ser convertido.
     */
    public ProdutoCadastroDto(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
    }
}
