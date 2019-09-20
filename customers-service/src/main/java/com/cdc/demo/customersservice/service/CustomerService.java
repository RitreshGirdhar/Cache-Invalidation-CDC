package com.cdc.demo.customersservice.service;

import com.cdc.demo.customersservice.dao.CustomerDao;
import com.cdc.demo.customersservice.dao.repositories.CustomerRepository;
import com.cdc.demo.customersservice.entity.CustomerEntity;
import com.cdc.demo.customersservice.models.CustomerResponse;
import com.cdc.demo.customersservice.transformer.CustomerDaoTransformer;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDaoTransformer customerDaoTransformer;

    public CustomerResponse getCustomer(String customerId) throws RuntimeException {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
        if (customerEntity.isPresent()) {
            return customerDaoTransformer.transform(customerEntity.get());
        }
        return CustomerResponse.builder().build();
    }
}
