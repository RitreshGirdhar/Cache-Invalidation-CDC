package com.cdc.demo.invoiceservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_history")
@Data
public class InvoiceHistory {

    private String id;
    private BigDecimal amount;

}
