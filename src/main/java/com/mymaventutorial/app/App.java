package com.mymaventutorial.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication // 
public class App 
{
    public String home(){
        return "Hello World";
    }
    
    public static void main( String[] args )
    {
        System.out.println( "Hello this work automatic!" );
        SpringApplication.run(App.class, args);
    }
}
