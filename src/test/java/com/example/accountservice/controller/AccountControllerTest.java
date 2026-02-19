package com.example.accountservice.controller;

import com.example.accountservice.dto.AccountDetail;
import com.example.accountservice.dto.AccountResponse;
import com.example.accountservice.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for AccountController.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void testCreateAccount_Success() throws Exception {
        String requestBody = "{ \"customerName\": \"Alice\", \"customerMobile\": \"5551234567\", \"customerEmail\": \"alice@example.com\" }";

        // Mock service behavior
        Mockito.when(accountService.createAccount(Mockito.any()))
                .thenReturn(new com.example.accountservice.model.Customer(1L, "CUST100",
                        "Alice", "5551234567", "alice@example.com", null, null, "S"));

        mockMvc.perform(post("/api/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateAccount_MissingField() throws Exception {
        String requestBody = "{ \"customerName\": \"Bob\", \"customerMobile\": \"\" }";
        mockMvc.perform(post("/api/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAccount_Success() throws Exception {
        AccountResponse response = new AccountResponse();
        response.setCustomerNumber("CUST200");
        response.setCustomerName("Charlie");
        response.setCustomerMobile("5559876543");
        response.setCustomerEmail("charlie@example.com");
        response.setAddress1("456 Elm St");
        response.setAddress2("");
        response.setAccountType("C");
        response.setAccounts(Arrays.asList(
                new AccountDetail("ACC123", "SAVINGS", 1000.0),
                new AccountDetail("ACC456", "CHECKING", 2000.0)
        ));

        Mockito.when(accountService.getAccountDetails("CUST200")).thenReturn(response);

        mockMvc.perform(get("/api/v1/account/CUST200"))
                .andExpect(status().isFound());
    }

    @Test
    public void testGetAccount_NotFound() throws Exception {
        Mockito.when(accountService.getAccountDetails("NOT_EXIST")).thenReturn(null);

        mockMvc.perform(get("/api/v1/account/NOT_EXIST"))
                .andExpect(status().isUnauthorized());
    }
}
