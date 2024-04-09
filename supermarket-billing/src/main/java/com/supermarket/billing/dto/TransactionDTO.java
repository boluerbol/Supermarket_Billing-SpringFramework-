package com.supermarket.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private Long clientId;
    private Long customerId;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime transactionTime;
}
