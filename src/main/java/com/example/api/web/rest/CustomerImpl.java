package com.example.api.web.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.api.domain.Customer;

public interface CustomerImpl {

    List<Customer> findAll();

    Customer findById(Long id);

    ResponseEntity<Customer> create(Customer customer);

    ResponseEntity<Customer> update(Customer customer);

    ResponseEntity delete(Long id);

}