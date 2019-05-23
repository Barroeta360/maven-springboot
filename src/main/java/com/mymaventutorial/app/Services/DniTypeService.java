/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.DAO.DniTypeRepository;
import com.mymaventutorial.app.Models.AppDniType;
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
public class DniTypeService {
    
    @Autowired
    private DniTypeRepository repository;
    
    @Cacheable(value="dnitype", key="123456")// save all in cache until any upload
    public List<AppDniType> getAll(){
        return (List<AppDniType>) repository.findAll();
    }
    
    public AppDniType getById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    @CacheEvict(value="dnitype", key="123456")//clean chache
    public AppDniType save(AppDniType entity){
        return repository.save(entity);
    }
    
    @CacheEvict(value="dnitype", key="123456")//clean chache
    public boolean delete(Long id){
        if(getById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
