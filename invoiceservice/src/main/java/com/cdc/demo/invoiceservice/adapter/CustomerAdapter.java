package com.cdc.demo.invoiceservice.adapter;

import com.cdc.demo.invoiceservice.models.CustomerDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "customer-service",url = "http://localhost:5050/")
public interface CustomerAdapter {

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{customerId}")
    CustomerDetails getCustomerDetails(@PathVariable(value = "customerId") String customerId);

}
