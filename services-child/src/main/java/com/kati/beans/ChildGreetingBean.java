package com.kati.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // Spring will give a default name the bean after the class name --> childGreetingBean  OR ("somename")
public class ChildGreetingBean {

    @Value("${greeting}")
    private String say;

    @Value("${show}")
    private String show;

    @Value("${name}")
    private String yourName;

    @Value("${sebi.user}")
    private String user;



    public String sayHello() {

        return "Hello from ChildGreetingBean!";

    }



}
