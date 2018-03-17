package com.kati;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CommonRestConfig extends RouteBuilder{


    public void configure() throws Exception {



        //common jetty config -- standard always like this can only have 1 in 1 camelcontext
        // that's why i put this into common lib
        restConfiguration()
                .component("jetty")
                .scheme("http")
                .host("localhost")
                .port("8084")
                .contextPath("/services")
                ;
    }
}
