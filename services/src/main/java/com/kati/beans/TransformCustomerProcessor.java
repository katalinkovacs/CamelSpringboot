package com.kati.beans;

import com.kati.common.model.Customer1;
import com.kati.common.model.Customer2;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;




@Component
public class TransformCustomerProcessor {


    Logger LOG = LoggerFactory.getLogger(TransformCustomerProcessor.class);


    public void transformCustomer(Exchange exchange){

        LOG.info("Customer transformation");


        Customer1 customer1 = (Customer1) exchange.getIn().getBody();

        Customer2 customer2 = new Customer2();


        LOG.info("transform from customer1 " + customer1.getGivenName() + " " + customer1.getFamilyName());
        customer2.setLastName(customer1.getFamilyName());
        customer2.setFirstName(customer1.getGivenName());

        LOG.info("transform in customer2 " + customer2.getFirstName()+ " " + customer2.getLastName());


        exchange.getOut().setBody(customer2);

    }


}
