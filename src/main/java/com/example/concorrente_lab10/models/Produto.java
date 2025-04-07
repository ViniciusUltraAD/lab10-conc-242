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

    private AtomicInteger countSold;

    public Produto(ProdutoPostDto produtoPostDto) {
        this.id = produtoPostDto.getId();
        this.name = produtoPostDto.getName();
        this.price = produtoPostDto.getPrice();
        this.quantity = new AtomicInteger(produtoPostDto.getQuantity());
        this.countSold = new AtomicInteger();
    }

    public void incrementarEstoque(@Positive(message = "Quantidade Tem que ser positiva") Integer quantity) {
        this.quantity.getAndSet(quantity);
    }

    public void compraRealizada(@Positive(message = "Quantidade Tem que ser positiva") Integer quantity) {
        int newQuantity = this.quantity.intValue() - quantity;
        this.quantity.getAndSet(newQuantity);
        int newCountSold = this.countSold.intValue() + quantity;
        this.countSold.getAndSet(newCountSold);
    }
}
