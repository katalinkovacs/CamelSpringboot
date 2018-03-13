package com.beansandroutes.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


//@Component
public class RestRouteException extends RouteBuilder {


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

        from("jetty://http://0.0.0.0:8082/say")
                .transform(method("myBean", "saySomething"))

                .setHeader(Exchange.HTTP_URI, simple("http4://localhost:8088/test"))
                .to("http4://localhost:8088/test?httpClient.socketTimeout=2000&httpClient.connectTimeout=2000")


                .log("log ${body}");
    }
}


