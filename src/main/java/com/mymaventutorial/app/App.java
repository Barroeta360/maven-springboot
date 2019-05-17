package com.mymaventutorial.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@ComponentScan //Allow to spring boot to detect multiple @Component
@SpringBootApplication // 
@EnableCaching
@EnableScheduling
public class App extends SpringBootServletInitializer{
    
    @Value("${spring.application.name}")
    private String name;
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
       return application.sources(App.class);
    }

    public static void main(String[] args) {
        System.out.println("Hello this work automatic!");
        SpringApplication.run(App.class, args);
    }
    
    
}
