package com.kati.routes;

import com.kati.Application;
import com.kati.Child1;
import com.kati.Child2;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("unittest")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes =  Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisableJmx(true)
public class ChildRoutesTest extends CamelTestSupport {

    //Must have this
    @Autowired
    private CamelContext camelContext;

    //Must have this
    protected CamelContext createCamelContext() throws Exception { return camelContext; }

    @Test
    public  void childPostRoute_whenCustomer1Input_thenCustomer2Output()throws Exception{

        // need escape character \"something\"  -->    \ before "
        String inputChild = "{\"id\" : \"1\", \"firstName\" : \"Katalin\", \"lastName\"  : \"Kovacs\", \"dateOfBirth\" : \"1975-04-09\"}";

        camelContext.start();

       Exchange out = template.request("direct:post-childroute", exchange -> {
           exchange.getIn().setBody(inputChild);
       });


        String outMsg = (String) out.getIn().getBody(String.class);

        //System.out.println(outMsg);


       assertEquals("{\"childId\":1,\"givenName\":\"Katalin\",\"familyName\":\"Kovacs\",\"dob\":\"09-04-1975\"}", outMsg);

        camelContext.stop();
    }



}
