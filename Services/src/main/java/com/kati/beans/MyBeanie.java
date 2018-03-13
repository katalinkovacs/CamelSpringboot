package com.kati.beans;

import com.kati.mylibrary.beans.MyBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBeanie {

    @Value("${greeting}")
    private String say;

    @Value("${show}")
    private String show;

    @Value("${name}")
    private String yourName;

    public String saySomething() {

        MyBean myBean = new MyBean();

        return say + myBean.giveMeSomeCrap();

    }


    public String showHello(){

        //MyBean myBean = new MyBean();
        return show + " I am showing HELLO";
    }

    public String showHello2(){

        MyBean myBean = new MyBean();
        return show + " I am showing HELLO 2 " +myBean.mySecondMethodInMyBean();
    }

    public String welcome(){

        //MyBean myBean = new MyBean();
        return "Welcome " +yourName;
    }
}
