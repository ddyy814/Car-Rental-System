package com.example.demo_car_rental.repository;

import com.example.demo_car_rental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

    List<Customer> findAll();

    Customer findById(Long id);
    
    Customer findByUsername(String username);
}
