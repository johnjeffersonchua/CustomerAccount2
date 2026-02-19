package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for account creation request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountRequest {
    /**
     * Customer name (required).
     */
    private String customerName;

    /**
     * Customer mobile (required).
     */
    private String customerMobile;

    /**
     * Customer email (required).
     */
    private String customerEmail;

    /**
     * Address line 1.
     */
    private String address1;

    /**
     * Address line 2.
     */
    private String address2;

    /**
     * Account type ('S' or 'C').
     */
    private String accountType;
}
