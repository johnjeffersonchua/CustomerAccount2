package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for account detail in customer inquiry response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountDetail {
    /**
     * Account number.
     */
    private String accountNumber;

    /**
     * Account type (e.g., SAVINGS or CHECKING).
     */
    private String accountType;

    /**
     * Account balance.
     */
    private double balance;
}
