package com.example.concorrente_lab10.models;

import com.example.concorrente_lab10.models.Dto.ProdutoPostDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    private String id;

    private String name;

    private Double price;

    private AtomicInteger quantity;

    private AtomicInteger count_sold;

    public Produto(ProdutoPostDto produtoPostDto) {
        this.id = produtoPostDto.getId();
        this.name = produtoPostDto.getName();
        this.price = produtoPostDto.getPrice();
        this.quantity = new AtomicInteger(produtoPostDto.getQuantity());
        this.count_sold = new AtomicInteger();
    }

    // Faz sentido o synchronized?
    public synchronized void incrementarEstoque(@Positive(message = "Quantidade Tem que ser positiva") Integer quantity) {
        int newQuantity = this.quantity.intValue() + quantity;
        this.quantity.getAndSet(newQuantity); // Faz sentido?
    }

    public synchronized void compraRealizada(@Positive(message = "Quantidade Tem que ser positiva") Integer quantity) {
        int newQuantity = this.quantity.intValue() - quantity;
        this.quantity.getAndSet(newQuantity); // Faz sentido?
        int newCountSold = this.count_sold.intValue() + quantity;
        this.count_sold.getAndSet(newCountSold);
    }
}
