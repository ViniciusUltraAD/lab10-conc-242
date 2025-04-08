package com.example.concorrente_lab10.models;

import com.example.concorrente_lab10.models.Dto.ProdutoPostDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um produto no sistema.
 *
 * Contém informações de identificação, nome, preço, quantidade em estoque e número de vendas realizadas.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    /**
     * Identificador único do produto.
     */
    @Id
    private String id;

    /**
     * Nome do produto.
     */
    private String name;

    /**
     * Preço do produto.
     */
    private Double price;

    /**
     * Quantidade disponível em estoque.
     */
    private Integer quantity;

    /**
     * Quantidade total de unidades vendidas.
     */
    private Integer countSold;

    /**
     * Construtor que inicializa um {@code Produto} com base nos dados recebidos via {@link ProdutoPostDto}.
     *
     * @param produtoPostDto DTO contendo os dados do produto.
     */
    public Produto(ProdutoPostDto produtoPostDto) {
        this.id = produtoPostDto.getId();
        this.name = produtoPostDto.getName();
        this.price = produtoPostDto.getPrice();
        this.quantity = produtoPostDto.getQuantity();
        this.countSold = 0;
    }

    /**
     * Atualiza a quantidade disponível no estoque.
     *
     * @param quantity Nova quantidade a ser definida.
     * @throws IllegalArgumentException se {@code quantity} for nulo ou não positivo.
     */
    public void incrementarEstoque(@Positive(message = "Quantidade Tem que ser positiva") Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Realiza a operação de compra, reduzindo o estoque e incrementando a contagem de vendas.
     *
     * @param quantity Quantidade comprada.
     * @throws IllegalArgumentException se {@code quantity} for nulo ou não positivo.
     */
    public void compraRealizada(@Positive(message = "Quantidade Tem que ser positiva") Integer quantity) {
        int newQuantity = this.quantity.intValue() - quantity;
        this.quantity = newQuantity;
        int newCountSold = this.countSold.intValue() + quantity;
        this.countSold = newCountSold;
    }
}
