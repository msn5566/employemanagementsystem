
package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String CustomerId;
    private String Name;
    private Contact Contact;
}