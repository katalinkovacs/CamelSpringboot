package com.kati;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component  // without this it will not be scanned!!!
public class CommonRestConfig extends RouteBuilder{


    public void configure() throws Exception {

        //common jetty config -- standard always like this
        restConfiguration()
                .component("jetty")
                .scheme("http")
                .host("localhost")
                .port("8084")
                .contextPath("/services")
        ;
    }
}
