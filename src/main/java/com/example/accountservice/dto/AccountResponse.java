package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO representing customer details along with account information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    /**
     * Customer number.
     */
    private String customerNumber;

    /**
     * Customer name.
     */
    private String customerName;

    /**
     * Customer mobile.
     */
    private String customerMobile;

    /**
     * Customer email.
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
     * Account type of the customer.
     */
    private String accountType;

    /**
     * List of dummy account details (savings and checking).
     */
    private List<AccountDetail> accounts;
}
