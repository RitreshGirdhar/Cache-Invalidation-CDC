package com.cdc.demo.invoiceservice.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@NonNull
public class CustomerDetails {

    private String name;
    private String address;
    private String id;
}
