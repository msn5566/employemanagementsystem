
package com.generated.microservice.model.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    private String Id;
    private String Date;
    private Client Client;
    private Products Products;
    private Payment Payment;
}