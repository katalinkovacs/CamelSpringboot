package com.beansandroutes.beans;

import com.mylibrary.beans.MyBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBeanie {

    @Value("${greeting}")
    private String say;

    public String saySomething() {

        MyBean myBean = new MyBean();

        return say + myBean.giveMeSomeCrap();

    }
}