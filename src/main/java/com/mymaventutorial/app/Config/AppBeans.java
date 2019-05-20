/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Config;

import com.mymaventutorial.app.DTO.CountryRest;
import com.mymaventutorial.app.DTO.EntityMasterRest;
import com.mymaventutorial.app.DTO.MessageRest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Keton
 */
@Configuration
public class AppBeans {
    
    @Bean
    public CountryRest getCountry(){
        return new CountryRest();
    }
    
    @Bean
    public EntityMasterRest getEntityMaster(){
        return new EntityMasterRest();
    }
    
    @Bean
    public MessageRest getMessage(){
        return new MessageRest();
    }
}
