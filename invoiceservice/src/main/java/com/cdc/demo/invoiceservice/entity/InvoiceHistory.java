package com.cdc.demo.invoiceservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceHistory {

    @Id
    private String id;
    private String customerId;
    private String description;
    private BigDecimal amount;
}
