package com.cdc.demo.invoiceservice.transformer;

import com.cdc.demo.invoiceservice.entity.InvoiceHistory;
import com.cdc.demo.invoiceservice.models.InvoiceResponse;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDaoTransformer extends Transformer<InvoiceHistory, InvoiceResponse> {

    @Override
    public InvoiceResponse transform(InvoiceHistory invoiceHistory) {
        return InvoiceResponse.builder()
                .amount(invoiceHistory.getAmount())
                .id(invoiceHistory.getId())
                .build();
    }


}
