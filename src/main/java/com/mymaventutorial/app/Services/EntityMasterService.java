/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.AppUtils.ModelMapperUtils;
import com.mymaventutorial.app.DAO.EntityMasterRepository;
import com.mymaventutorial.app.DTO.EntityMasterRest;
import com.mymaventutorial.app.Models.AppEntityMaster;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Keton
 */
@Service
public class EntityMasterService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    
    @Autowired
    private EntityMasterRepository repository;
    
    @Cacheable(value="EM", key="123456")// save in cache until any upload
    public List<AppEntityMaster> getAll(){
        LOGGER.info("Get all entities master");
        return repository.findAll();
    } 
    
    public AppEntityMaster getById(Long id){
        LOGGER.info("Get entity master with id: " + id);
        return repository.findById(id).orElse(null);
    }
    
    @CacheEvict(value="EM", key="123456")//clean chache
    @Transactional
    public AppEntityMaster save(AppEntityMaster entity){
        LOGGER.info("Creating an entity master");
        return repository.save(entity);
    }
    
    @CacheEvict(value="EM", key="123456")//clean chache
    @Transactional
    public boolean delete(Long id){
        if(getById(id) != null){
            LOGGER.info("Deleting entity with id: " + id);
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    
    //Mapping
    
    //From Rest
    
    public AppEntityMaster mapFromRest(EntityMasterRest rest){
        AppEntityMaster entity = ModelMapperUtils.map(rest, AppEntityMaster.class);
        return entity;
    }
    
    public List<AppEntityMaster> mapFromRest(List<EntityMasterRest> rest){
        List<AppEntityMaster> list = new ArrayList<>();
        if(rest != null)
            rest.forEach(item -> list.add(mapFromRest(item)));
        return list;
    }
    
    //To Rest
    
    public EntityMasterRest mapToRest(AppEntityMaster entity){
        EntityMasterRest rest = ModelMapperUtils.map(entity, EntityMasterRest.class);
        return rest;
    }
    
    public List<EntityMasterRest> mapToRest(List<AppEntityMaster> entity){
        List<EntityMasterRest> rest = new ArrayList<>();
        if(entity != null)
            entity.forEach(item -> rest.add(mapToRest(item)));
        return rest;
    }
    
    
}
