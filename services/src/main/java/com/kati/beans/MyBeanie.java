package com.kati.beans;

import com.kati.mylibrary.beans.MyBean;
import model.User;
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

    @Value("${sebi.user}")
    private String user;



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

    public String welcome2(){

        //MyBean myBean = new MyBean();
        return "Welcome " +yourName;
    }

    public String welcome(){

        MyBean myBean = new MyBean();
        return "Welcome " +yourName +"! " +myBean.hi();
    }

    public String method1(){

        MyBean myBean = new MyBean();

        User user1 = new User();
        user1.setFirstName("Sebastian");
        user1.setLastName("Kovacs");

        return "Hello " +user1.getFirstName() +"! " +myBean.hi();

    }

    public String method2(){

        MyBean myBean = new MyBean();

        return "Hello " +user +"! " +myBean.hi();

    }

    public String myBeanieMethod(){

        MyBean myBean = new MyBean();
        return "Welcome in my 'myBeanieMethod'! " +myBean.hi();
    }


}
