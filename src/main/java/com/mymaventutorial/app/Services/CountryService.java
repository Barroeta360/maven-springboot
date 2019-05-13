/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.AppUtils.ModelMapperUtils;
import com.mymaventutorial.app.DAO.CountryRepository;
import com.mymaventutorial.app.DTO.CountryRest;
import com.mymaventutorial.app.Models.AppCountry;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Keton
 */
@Service
public class CountryService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    
    @Autowired
    private CountryRepository repository;
    
    @Cacheable(value="Countries", key="123456")// save in cache all countries until any upload
    public List<AppCountry> getAll(){
        LOGGER.info("Get all countries");
        return repository.findAll();
    }
    
    @CacheEvict(value="Countries", key="123456")//clean chache of countries
    //@CachePut(value="Countries", key="123456")
    @Transactional
    public AppCountry save(AppCountry entity){
         LOGGER.info("Save country with name " + entity.getName());
         return repository.save(entity);
    }
    
    public AppCountry getById(Long id){
         LOGGER.info("Get country by id: " + id);
         return repository.findById(id).orElse(null);
    }
    
    public AppCountry getByName(String name){
         LOGGER.info("Get country by name: " + name);
         return repository.findByName(name);
    }
    
    @CacheEvict(value="Countries", key="123456")//clean chache of countries
    //@CachePut(value="Countries", key="123456")
    @Transactional
    public boolean delete(Long id){
        if(getById(id) != null){
            LOGGER.info("Delete country by id: " +id);
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    
    //Mapping
    
    //From Rest
    
    public AppCountry mapFromRest(CountryRest rest){
        AppCountry entity = ModelMapperUtils.map(rest, AppCountry.class);
        return entity;
    }
    
    public List<AppCountry> mapFromRest(List<CountryRest> rest){
        List<AppCountry> list = new ArrayList<>();
        if(rest != null)
            rest.forEach(item -> list.add(mapFromRest(item)));
        return list;
    }
    
    //To Rest
    
    public CountryRest mapToRest(AppCountry entity){
        CountryRest rest = ModelMapperUtils.map(entity, CountryRest.class);
        return rest;
    }
    
    public List<CountryRest> mapToRest(List<AppCountry> entity){
        List<CountryRest> rest = new ArrayList<>();
        if(entity != null)
            entity.forEach(item -> rest.add(mapToRest(item)));
        return rest;
    }
    
}
