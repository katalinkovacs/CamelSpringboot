package com.kati.routes;

import com.kati.beans.Child1;
import com.kati.beans.Child2;
import com.kati.beans.TransformChildProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChildRoutes extends RouteBuilder {


    @Autowired   //REFERENCE
    TransformChildProcessor transformChildProcessor;

    DataFormat child1DataFormat = new JacksonDataFormat(Child1.class);
    DataFormat child2DataFormat = new JacksonDataFormat(Child2.class);


    @Override
    public void configure() throws Exception {

        getContext().setStreamCaching(true);

        onException(Throwable.class)
                .handled(true)
                .log("gotException")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("501"))
                .setHeader(Exchange.HTTP_RESPONSE_TEXT, simple("Not implemented"))
                .removeHeader(Exchange.EXCEPTION_CAUGHT)
        ;


        //common jetty config -- standard always like this
        restConfiguration()
                .component("jetty")
                .scheme("http")
                .host("localhost")
                .port("8083")
                .contextPath("/services")
        ;

        //common rest config -- standard always like this -- like restcontroller in MVC
        rest()
                .get("/child") //http get request comes here and gets routed to direct:get-route -- this can be invoked from browser
                .route()
                .to("direct:get-route")
                .endRest()

                .post("/child") //http post request comes here and gets routed to direct:post-route -- you NEED TO USE POSTMAN FOR THIS!!!!!
                .route()
                .to("direct:post-route")
                .endRest()
        ;


        // the request from .post("/show") comes here
        //these are like your model and view in MVC
        from("direct:post-route")
                .unmarshal(child1DataFormat)
                .bean(transformChildProcessor, "transformChild")
                .marshal(child2DataFormat)
                .log("log ${body}");


        // the request from .get("/show") comes here -- this can be invoked from browser
        from("direct:get-route")
                .transform(method("myBean", "myBeanieMethod"))
                .setHeader(Exchange.CONTENT_TYPE, simple("text/plain"))
                .log("log ${body}");



    }




}
