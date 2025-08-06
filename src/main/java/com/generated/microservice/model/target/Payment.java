
package com.generated.microservice.model.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String CustomerId;
    private BigDecimal Amount;
    private String Status;
}