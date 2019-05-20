/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Config;

//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Keton
 */
@EnableWebMvc
@Configuration
//@ConfigurationProperties(prefix = "prefix")
public class WebMVCConfig implements WebMvcConfigurer{
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedOrigins("*")
                        //.allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS", "HEAD");
                        .allowedMethods("*");
/*
                registry.addMapping("/country")
                        .allowedOrigins("http://localhost:4200")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS");*/
            }
        };
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
