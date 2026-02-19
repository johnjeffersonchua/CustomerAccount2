package com.example.accountservice.controller;

import com.example.accountservice.dto.AccountRequest;
import com.example.accountservice.dto.AccountResponse;
import com.example.accountservice.model.Customer;
import com.example.accountservice.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for account operations.
 */
@RestController
@RequestMapping("/api/v1/account")
@Log4j2
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Endpoint to create a new customer account.
     *
     * @param request Account creation request payload.
     * @return ResponseEntity with created customer or error.
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request) {
        log.info("Received account creation request for: {}", request.getCustomerName());
        // Validate required fields
        if (!StringUtils.hasText(request.getCustomerName()) ||
                !StringUtils.hasText(request.getCustomerMobile()) ||
                !StringUtils.hasText(request.getCustomerEmail())) {
            log.error("Missing required fields in account creation request.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("customerName, customerMobile, and customerEmail are required.");
        }

        // Map request to entity
        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setCustomerMobile(request.getCustomerMobile());
        customer.setCustomerEmail(request.getCustomerEmail());
        customer.setAddress1(request.getAddress1());
        customer.setAddress2(request.getAddress2());
        customer.setAccountType(request.getAccountType());

        Customer created = accountService.createAccount(customer);
        log.info("Account creation successful for customerNumber: {}", created.getCustomerNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Endpoint to get customer account details by customer number.
     *
     * @param customerNumber Customer number to search.
     * @return ResponseEntity with customer and account details or error.
     */
    @GetMapping("/{customerNumber}")
    public ResponseEntity<?> getAccount(@PathVariable String customerNumber) {
        log.info("Received account inquiry for customerNumber: {}", customerNumber);
        AccountResponse response = accountService.getAccountDetails(customerNumber);
        if (response == null) {
            log.error("Customer not found for number: {}", customerNumber);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Customer not found");
        }
        log.info("Account inquiry successful for customerNumber: {}", customerNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
