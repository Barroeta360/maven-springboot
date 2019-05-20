/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.AppUtils.ModelMapperUtils;
import com.mymaventutorial.app.DAO.MessageRepository;
import com.mymaventutorial.app.DTO.MessageRest;
import com.mymaventutorial.app.Models.AppMessage;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Keton
 */
@Service
public class MessageService {
    
    @Autowired
    MessageRepository repository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    
    public AppMessage save(AppMessage entity){
        return repository.save(entity);
    }
    
    public AppMessage getById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    public List<AppMessage> getByReceiver(Long id){
        return repository.findByTo(id);
    }
    
    public boolean delete(Long id){
        if(getById(id) != null){
            LOGGER.info("Delete message by id: " +id);
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
    //Mapping
    
    //From Rest
    
    public AppMessage mapFromRest(MessageRest rest){
        AppMessage entity = ModelMapperUtils.map(rest, AppMessage.class);
        return entity;
    }
    
    public List<AppMessage> mapFromRest(List<MessageRest> rest){
        List<AppMessage> list = new ArrayList<>();
        if(rest != null)
            rest.forEach(item -> list.add(mapFromRest(item)));
        return list;
    }
    
    //To Rest
    
    public MessageRest mapToRest(AppMessage entity){
        MessageRest rest = ModelMapperUtils.map(entity, MessageRest.class);
        return rest;
    }
    
    public List<MessageRest> mapToRest(List<AppMessage> entity){
        List<MessageRest> rest = new ArrayList<>();
        if(entity != null)
            entity.forEach(item -> rest.add(mapToRest(item)));
        return rest;
    }
}
