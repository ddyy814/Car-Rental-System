package com.example.demo_car_rental.service;

import com.example.demo_car_rental.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(Long id);
    
    Customer findByUsername(String username);

    Customer save(Customer customer);
}
