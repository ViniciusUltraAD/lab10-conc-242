package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de resposta para a operação de cadastro de um produto.
 *
 * <p>Contém uma mensagem de sucesso e os dados do produto recém-cadastrado.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseCadastroDto {

    /**
     * Mensagem informando o status da operação.
     */
    private String message;

    /**
     * Dados do produto cadastrado.
     */
    private ProdutoCadastroDto produtoCadastroDto;

    /**
     * Construtor que gera automaticamente a resposta a partir de um objeto {@link Produto}.
     *
     * @param produto O produto que foi cadastrado com sucesso.
     */
    public ProdutoResponseCadastroDto(Produto produto) {
        this.message = "Produto cadastrado com sucesso.";
        this.produtoCadastroDto = new ProdutoCadastroDto(produto);    }
}
