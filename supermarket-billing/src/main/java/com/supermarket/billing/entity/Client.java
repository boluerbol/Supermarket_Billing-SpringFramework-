package com.supermarket.billing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    // Getters and setters
}