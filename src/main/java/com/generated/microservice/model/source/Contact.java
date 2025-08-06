
package com.generated.microservice.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private String Email;
    private String Phone;

    private String FirstName;
    private String LastName;
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
}