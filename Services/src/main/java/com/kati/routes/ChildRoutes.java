package com.kati.routes;

import com.kati.beans.Child1;
import com.kati.beans.Child2;
import com.kati.beans.TransformChildProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.kati.mylibrary.route.constants.Constants.SIMPLE_BODY;
import static com.kati.mylibrary.route.constants.Constants.STEP_FINISH;
import static com.kati.mylibrary.route.constants.Constants.STEP_START;

@Component
public class ChildRoutes extends RouteBuilder {


    final static Logger logger = LoggerFactory.getLogger(ChildRoutes.class);
    public com.kati.mylibrary.logger.StandardLogger stdLog = new com.kati.mylibrary.logger.StandardLogger();


    @Autowired   //REFERENCE
    TransformChildProcessor transformChildProcessor;

    DataFormat child1DataFormat = new JacksonDataFormat(Child1.class);
    DataFormat child2DataFormat = new JacksonDataFormat(Child2.class);


    @Override
    public void configure() throws Exception {

        logger.info("-----------------Entering application----------------.");

        getContext().setStreamCaching(true);

        stdLog.setDefaultInterface_id("This is a default_Interface_id");
        stdLog.setDefaultInterface_step("This is a default_Interface_step");


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
                //.to("direct:get-route")
                .to("direct:get-childroute")
                .endRest()

                .post("/child") //http post request comes here and gets routed to direct:post-route -- you NEED TO USE POSTMAN FOR THIS!!!!!
                .route()
                //.to("direct:post-route")
                .to("direct:post-childroute")
                .endRest()
        ;



        // the request from .post("/show") comes here
        //these are like your model and view in MVC
        from("direct:post-childroute")
                .log(STEP_START)
                .bean(stdLog, "logStart")
                .log(SIMPLE_BODY)
                .log(LoggingLevel.INFO,"This is INFO log .......")
                .unmarshal(child1DataFormat)
                .bean(transformChildProcessor, "transformChild")
                .marshal(child2DataFormat)
                .log("log ${body}")
                .bean(stdLog, "logFinished")
                .log(STEP_FINISH);


        // the request from .get("/show") comes here -- this can be invoked from browser
        from("direct:get-childroute")
                .log(STEP_START)
                .bean(stdLog, "logStart")
                .log(SIMPLE_BODY)
                .log(LoggingLevel.INFO,"This is INFO log .......")
                .transform(method("myBean", "myBeanieMethod"))
                .setHeader(Exchange.CONTENT_TYPE, simple("text/plain"))
                .log("log ${body}")
                .bean(stdLog, "logFinished")
                .log(STEP_FINISH);



    }




}
