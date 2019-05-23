/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.AppUtils.ModelMapperUtils;
import com.mymaventutorial.app.DAO.StateRepository;
import com.mymaventutorial.app.DTO.StateRest;
import com.mymaventutorial.app.Models.AppCountry;
import com.mymaventutorial.app.Models.AppState;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Keton
 */
@Service
public class StateService {
    
    @Autowired
    private StateRepository repository;
    
    @Cacheable(value="states", key="123456")// save all in cache until any upload
    public List<AppState> getAll(){
        return repository.findAll();
    }
    
    public List<AppState> getByCountry(AppCountry country){
        return repository.findByCountry(country);
    }
    
    public AppState getById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    @CacheEvict(value="states", key="123456")//clean chache
    public AppState save(AppState entity){
        return repository.save(entity);
    }
    
    @CacheEvict(value="states", key="123456")//clean chache
    public boolean delete(Long id){
        if(repository.findById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
    //MAPPING
    
    //from rest
    
    public AppState mapFromRest(StateRest rest){
        return ModelMapperUtils.map(rest, AppState.class);
    }
    
    public List<AppState> mapFromRest(List<StateRest> rest){
        return ModelMapperUtils.mapList(rest, AppState.class);
    }
    
    // to rest
    
    public StateRest mapToRest(AppState entity){
        return ModelMapperUtils.map(entity, StateRest.class);
    }
    
    public List<StateRest> mapToRest(List<AppState> rest){
        return ModelMapperUtils.mapList(rest, StateRest.class);
    }
}

