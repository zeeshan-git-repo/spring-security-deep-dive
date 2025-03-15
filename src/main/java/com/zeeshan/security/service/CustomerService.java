package com.zeeshan.security.service;

import com.zeeshan.security.entity.Customer;
import com.zeeshan.security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository repository;

    public boolean saveCustomer(Customer customer) {
        // password is encrypted before saving a record
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        Customer customer1 = repository.save(customer);
        return customer1.getCid() != null;
    }

    /**
     * loadUserByUsername() will be called by authentication provider, spring will take care of this method.
     * loadUserByUsername() will load user record.
     */

    @Override
    public UserDetails loadUserByUsername(String customerEmail) throws UsernameNotFoundException {
        Customer customer = repository.findByCustomerEmail(customerEmail);
        return new User(customer.getCustomerEmail(),customer.getPassword(), Collections.emptyList());
    }
}
