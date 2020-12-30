package com.example.demo_car_rental.service;

import com.example.demo_car_rental.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImplement implements UserDetailsService {
	@Autowired
	private CustomerService customerService;
	
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Customer customer = customerService.findByUsername(username);
    	List<GrantedAuthority> authorities = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return buildUserForAuthentication(customer, authorities);
    }
    
    private UserDetails buildUserForAuthentication(Customer customer, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(),
                true, true, true, true, authorities);
    }
}
