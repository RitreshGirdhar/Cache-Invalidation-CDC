package com.cdc.demo.customersservice.dao.repositories;

import com.cdc.demo.customersservice.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity,String> {

}
