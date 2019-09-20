package com.cdc.demo.customersservice.transformer;

import com.cdc.demo.customersservice.entity.CustomerEntity;
import com.cdc.demo.customersservice.models.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerDaoTransformer extends Transformer<CustomerEntity, CustomerResponse>{

    @Override
    public CustomerResponse transform(CustomerEntity customerEntity) {
        return CustomerResponse.builder()
                .address(customerEntity.getAddress())
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .build();
    }
}
