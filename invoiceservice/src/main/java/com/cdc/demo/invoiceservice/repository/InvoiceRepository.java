package com.cdc.demo.invoiceservice.repository;

import com.cdc.demo.invoiceservice.entity.InvoiceHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceHistory, String> {

    Optional<List<InvoiceHistory>> findAllByCustomerId(String customerId);
}
