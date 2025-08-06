
package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String OrderId;
    private String OrderDate;
    private Customer Customer;
    private Items Items;
    private Billing Billing;
}