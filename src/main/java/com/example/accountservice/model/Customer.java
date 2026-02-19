package com.example.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity representing a Customer.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")

public class Customer {
    /**
     * Unique database identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique customer number.
     */
    @Column(unique = true)
    private String customerNumber;

    /**
     * Customer's name.
     */
    private String customerName;

    /**
     * Customer's mobile number.
     */
    private String customerMobile;

    /**
     * Customer's email address.
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
     * Account type (S for savings, C for checking).
     */
    private String accountType;
}
