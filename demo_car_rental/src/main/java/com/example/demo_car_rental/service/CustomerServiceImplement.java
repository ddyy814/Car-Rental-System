package com.example.demo_car_rental.service;

import com.example.demo_car_rental.model.Customer;
import com.example.demo_car_rental.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplement implements CustomerService{

	@Autowired
    private CustomerDAO customerDAO;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImplement(CustomerDAO customerDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerDAO = customerDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Customer findById(Long id) {
        return this.customerDAO.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return this.customerDAO.findAll();
    }

    @Override
    public Customer save(Customer customer) {
    	customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return customerDAO.save(customer);
    }

	@Override
	public Customer findByUsername(String username) {
		return this.customerDAO.findByUsername(username);
	}
}
