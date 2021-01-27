package com.cdc.demo.customersservice.controller;

import com.cdc.demo.customersservice.models.CustomerResponse;
import com.cdc.demo.customersservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customer/{customerId}")
    public CustomerResponse getCustomer(@PathVariable(value = "customerId") String customerId) {
        return customerService.getCustomer(customerId);
    }

}
