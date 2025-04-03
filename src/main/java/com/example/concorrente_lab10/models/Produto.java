package com.example.concorrente_lab10.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Data
public class Produto {

    @Id
    private String id;

    private String name;

    private Double price;

    private AtomicInteger quantity;

    private AtomicInteger count_sold;


}
