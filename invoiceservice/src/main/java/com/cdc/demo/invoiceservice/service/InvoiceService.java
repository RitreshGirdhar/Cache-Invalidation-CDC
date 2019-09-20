package com.cdc.demo.invoiceservice.service;

import com.cdc.demo.invoiceservice.adapter.CustomerAdapter;
import com.cdc.demo.invoiceservice.entity.InvoiceHistory;
import com.cdc.demo.invoiceservice.models.CustomerDetails;
import com.cdc.demo.invoiceservice.models.InvoiceResponse;
import com.cdc.demo.invoiceservice.repository.InvoiceRepository;
import com.cdc.demo.invoiceservice.transformer.InvoiceDaoTransformer;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerAdapter customerAdapter;

    @Autowired
    private InvoiceDaoTransformer invoiceDaoTransformer;

    public List<InvoiceResponse> getInvoiceHistory(String customerId) {
        Optional<List<InvoiceHistory>> invoiceHistoryList = invoiceRepository.findAllByCustomerId(customerId);
        CustomerDetails customerDetails = customerAdapter.getCustomerDetails(customerId);

        if (invoiceHistoryList.isPresent()) {
            List<InvoiceResponse> invoiceResponses = invoiceDaoTransformer.transformList(invoiceHistoryList.get());
            invoiceResponses.stream().forEach(invoiceResponse -> {
                invoiceResponse.setCustomerAddress(customerDetails.getAddress());
                invoiceResponse.setCustomerName(customerDetails.getName());
            });
            return invoiceResponses;
        }
        return Lists.newArrayList();
    }
}
