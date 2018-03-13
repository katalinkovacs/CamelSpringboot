package com.kati.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Route02 extends RouteBuilder{


    @Override
    public void configure() throws Exception {
        getContext().setStreamCaching(true);

        onException(Throwable.class)
                .handled(true)
                .log("gotException")

                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("501"))
                .setHeader(Exchange.HTTP_RESPONSE_TEXT, simple("Not implemented"))
                .removeHeader(Exchange.EXCEPTION_CAUGHT)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("hello");

                    }
                })
        ;

        from("jetty://http://0.0.0.0:8082/show2")
                .transform(method("myBean", "showHello2"))

                .log("log ${body}");
    }



}
