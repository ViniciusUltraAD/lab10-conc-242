package com.example.concorrente_lab10.models;

import com.example.concorrente_lab10.models.Dto.ProdutoPostDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Entidade que representa um produto no sistema.
 *
 * <p>Contém informações de identificação, nome, preço, quantidade em estoque e número de vendas realizadas.
 * Utiliza {@link AtomicInteger} para garantir segurança em operações concorrentes de leitura e escrita.</p>
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
     * Quantidade disponível em estoque. Representado por {@link AtomicInteger} para segurança em concorrência.
     */
    private AtomicInteger quantity;

    /**
     * Quantidade total de unidades vendidas.
     */
    private AtomicInteger countSold;

    /**
     * Construtor que inicializa um {@code Produto} com base nos dados recebidos via {@link ProdutoPostDto}.
     *
     * @param produtoPostDto DTO contendo os dados do produto.
     */
    public Produto(ProdutoPostDto produtoPostDto) {
        this.id = produtoPostDto.getId();
        this.name = produtoPostDto.getName();
        this.price = produtoPostDto.getPrice();
        this.quantity = new AtomicInteger(produtoPostDto.getQuantity());
        this.countSold = new AtomicInteger();
    }

    /**
     * Atualiza a quantidade disponível no estoque.
     *
     * @param quantity Nova quantidade a ser definida.
     * @throws IllegalArgumentException se {@code quantity} for nulo ou não positivo.
     */
    public void incrementarEstoque(@Positive(message = "Quantidade Tem que ser positiva") Integer quantity) {
        this.quantity.getAndSet(quantity);
    }

    /**
     * Realiza a operação de compra, reduzindo o estoque e incrementando a contagem de vendas.
     *
     * @param quantity Quantidade comprada.
     * @throws IllegalArgumentException se {@code quantity} for nulo ou não positivo.
     */
    public void compraRealizada(@Positive(message = "Quantidade Tem que ser positiva") Integer quantity) {
        int newQuantity = this.quantity.intValue() - quantity;
        this.quantity.getAndSet(newQuantity);
        int newCountSold = this.countSold.intValue() + quantity;
        this.countSold.getAndSet(newCountSold);
    }
}
