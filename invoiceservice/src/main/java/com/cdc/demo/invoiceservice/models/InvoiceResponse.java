package com.cdc.demo.invoiceservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {

    private String id;
    private String customerName;
    private String customerAddress;

    private BigDecimal amount;
}
