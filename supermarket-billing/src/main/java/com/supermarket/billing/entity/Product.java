package com.supermarket.billing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "products")
    private Set<Transaction> transactions = new HashSet<>();

    // Getters and setters
}