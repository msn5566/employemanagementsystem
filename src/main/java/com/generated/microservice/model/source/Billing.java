
package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billing {

    private String CustomerId;
    private BigDecimal TotalAmount;
    private Boolean Paid;

    public void setTotal(BigDecimal totalAmount) {
        this.TotalAmount = totalAmount;
    }
}