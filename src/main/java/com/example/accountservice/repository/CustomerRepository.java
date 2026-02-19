package com.example.accountservice.repository;

import com.example.accountservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Customer data access.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Find a customer by their unique customer number.
     *
     * @param customerNumber Unique customer number.
     * @return Optional containing the Customer if found.
     */
    Optional<Customer> findByCustomerNumber(String customerNumber);
}
