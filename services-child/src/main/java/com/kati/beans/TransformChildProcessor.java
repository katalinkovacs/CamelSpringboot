package com.kati.beans;

import com.kati.Child1;
import com.kati.Child2;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;


@Component
public class TransformChildProcessor {


    public void transformChild(Exchange exchange){

        Child1 child1 = (Child1) exchange.getIn().getBody();

        Child2 child2 = new Child2();

        // Child1 : id,       firstName,  lastName,    dateOfBirth
        // Child2 : childId,  givenName,  familyName,  dob

        child2.setChildId(child1.getId());
        child2.setGivenName(child1.getFirstName());
        child2.setFamilyName(child1.getLastName());
        child2.setDob(child1.getDateOfBirth());

        exchange.getOut().setBody(child2);

    }
}
