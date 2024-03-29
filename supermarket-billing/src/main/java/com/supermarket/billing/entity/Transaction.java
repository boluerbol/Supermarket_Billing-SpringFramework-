package com.supermarket.billing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NotBlank(message = "Quantity is required")
    private int quantity;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id") // many to one
    private Client client;
    private String description;

    // Getters and setters
}
