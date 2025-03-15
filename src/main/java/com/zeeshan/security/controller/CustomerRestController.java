package com.zeeshan.security.controller;

import com.zeeshan.security.entity.Customer;
import com.zeeshan.security.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        boolean status = customerService.saveCustomer(customer);
        if (status) {
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> customerLogin(@RequestBody Customer customer){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customer.getCustomerEmail(),customer.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        boolean status = authentication.isAuthenticated();
        if(status){
            return new ResponseEntity<>("Welcome", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed to login", HttpStatus.BAD_REQUEST);
        }
    }

}
