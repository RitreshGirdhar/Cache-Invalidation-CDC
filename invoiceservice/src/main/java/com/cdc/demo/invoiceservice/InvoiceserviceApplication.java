package com.cdc.demo.invoiceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvoiceserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceserviceApplication.class, args);
    }

}
