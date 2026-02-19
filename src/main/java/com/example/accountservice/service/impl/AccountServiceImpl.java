package com.example.accountservice.service.impl;

import com.example.accountservice.dto.AccountDetail;
import com.example.accountservice.dto.AccountResponse;
import com.example.accountservice.model.Customer;
import com.example.accountservice.repository.CustomerRepository;
import com.example.accountservice.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Implementation of AccountService.
 */
@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final CustomerRepository customerRepository;
    private final Random random = new Random();

    public AccountServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer createAccount(Customer customer) {
        log.info("Creating account for customer: {}", customer.getCustomerName());
        // Generate random customer number
        String customerNumber = "CUST" + Math.abs(random.nextInt());
        customer.setCustomerNumber(customerNumber);
        // Save customer to the database
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer created with number: {}", savedCustomer.getCustomerNumber());
        return savedCustomer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountResponse getAccountDetails(String customerNumber) {
        log.info("Fetching account details for customer number: {}", customerNumber);
        Optional<Customer> customerOpt = customerRepository.findByCustomerNumber(customerNumber);
        if (!customerOpt.isPresent()) {
            log.warn("Customer not found: {}", customerNumber);
            return null;
        }

        Customer customer = customerOpt.get();
        // Generate two dummy accounts (one savings, one checking)
        List<AccountDetail> accounts = new ArrayList<>();
        String savingsAccountNumber = "ACC" + Math.abs(random.nextInt());
        String checkingAccountNumber = "ACC" + Math.abs(random.nextInt());
        accounts.add(new AccountDetail(savingsAccountNumber, "SAVINGS", 5000.0));
        accounts.add(new AccountDetail(checkingAccountNumber, "CHECKING", 2000.0));

        AccountResponse response = new AccountResponse(
                customer.getCustomerNumber(),
                customer.getCustomerName(),
                customer.getCustomerMobile(),
                customer.getCustomerEmail(),
                customer.getAddress1(),
                customer.getAddress2(),
                customer.getAccountType(),
                accounts);
        log.info("Account details prepared for customer: {}", customerNumber);
        return response;
    }
}
