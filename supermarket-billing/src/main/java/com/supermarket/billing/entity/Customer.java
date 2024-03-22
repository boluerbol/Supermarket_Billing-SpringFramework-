package com.supermarket.billing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Builder
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar(7)")
    private Long id;
    @Column(length = 100)
    private String name;
    private String address;
    private String email;



    @OneToMany(mappedBy = "customer")// one to many
    private List<Transaction> transactions;

}
