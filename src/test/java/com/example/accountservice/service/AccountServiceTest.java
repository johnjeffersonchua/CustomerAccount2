package com.example.accountservice.service;

import com.example.accountservice.dto.AccountResponse;
import com.example.accountservice.model.Customer;
import com.example.accountservice.repository.CustomerRepository;
import com.example.accountservice.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Unit tests for AccountServiceImpl.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setCustomerName("John Doe");
        customer.setCustomerMobile("1234567890");
        customer.setCustomerEmail("john@example.com");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 100");
        customer.setAccountType("S");
    }

    @Test
    public void testCreateAccount() {
        // Mock repository save
        Customer savedCustomer = new Customer(1L, "CUST100", customer.getCustomerName(),
                customer.getCustomerMobile(), customer.getCustomerEmail(),
                customer.getAddress1(), customer.getAddress2(), customer.getAccountType());
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        Customer result = accountService.createAccount(customer);
        assertNotNull("Created customer should not be null", result);
        assertEquals("Customer number should match", "CUST100", result.getCustomerNumber());
        Mockito.verify(customerRepository, Mockito.times(1)).save(any(Customer.class));
    }

    @Test
    public void testGetAccountDetails_Found() {
        // Mock repository findByCustomerNumber
        customer.setCustomerNumber("CUST200");
        Mockito.when(customerRepository.findByCustomerNumber("CUST200"))
                .thenReturn(Optional.of(customer));

        AccountResponse response = accountService.getAccountDetails("CUST200");
        assertNotNull("Response should not be null", response);
        assertEquals("Customer number should match", "CUST200", response.getCustomerNumber());
        assertNotNull("Accounts should not be null", response.getAccounts());
        assertEquals("Should have 2 dummy accounts", 2, response.getAccounts().size());
    }

    @Test
    public void testGetAccountDetails_NotFound() {
        Mockito.when(customerRepository.findByCustomerNumber("NON_EXISTENT"))
                .thenReturn(Optional.empty());

        AccountResponse response = accountService.getAccountDetails("NON_EXISTENT");
        assertNull("Response should be null when not found", response);
    }
}
