package com.kati;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.kati")
public class Application {

    // This class MUST have a main method --> spring-boot can run
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
