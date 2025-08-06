
package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String ItemId;
    private String Description;
    private int Quantity;
    private double Price;
}