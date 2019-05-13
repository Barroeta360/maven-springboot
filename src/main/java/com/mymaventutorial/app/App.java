package com.mymaventutorial.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@ComponentScan //Allow to spring boot to detect multiple @Component
@SpringBootApplication // 
@EnableCaching
public class App {

    public static void main(String[] args) {
        System.out.println("Hello this work automatic!");
        SpringApplication.run(App.class, args);
    }

}
