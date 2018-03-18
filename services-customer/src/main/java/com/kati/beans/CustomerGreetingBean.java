package com.kati.beans;

import org.springframework.stereotype.Component;

@Component
public class CustomerGreetingBean {


    public String sayHello() {

        return "Hello from CustomerGreetingBean!";

    }
}
