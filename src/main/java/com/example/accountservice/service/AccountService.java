package com.example.accountservice.service;

import com.example.accountservice.dto.AccountResponse;
import com.example.accountservice.model.Customer;

/**
 * Service interface for account operations.
 */
public interface AccountService {
    /**
     * Creates a new customer account.
     *
     * @param customer Customer entity to create.
     * @return Created Customer entity.
     */
    Customer createAccount(Customer customer);

    /**
     * Retrieves account details for a given customer number.
     *
     * @param customerNumber Unique customer number.
     * @return AccountResponse containing customer and account info, or null if not found.
     */
    AccountResponse getAccountDetails(String customerNumber);
}
