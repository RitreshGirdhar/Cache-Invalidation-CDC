package com.cdc.demo.invoiceservice.controller;

import com.cdc.demo.invoiceservice.models.InvoiceResponse;
import com.cdc.demo.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "/invoice/{customerId}")
    public InvoiceResponse getInvoice(@PathVariable(value = "customerId") String customerId) {
        return invoiceService.getInvoice(customerId);
    }

}
