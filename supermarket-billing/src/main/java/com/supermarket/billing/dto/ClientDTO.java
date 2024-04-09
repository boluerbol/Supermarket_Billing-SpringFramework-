package com.supermarket.billing.dto;

import com.supermarket.billing.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String companyName;
    private List<Transaction> transactions;
}
