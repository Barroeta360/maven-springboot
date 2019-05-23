/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.DAO.EntityTypeRepository;
import com.mymaventutorial.app.Models.AppEntityType;
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
public class EntityTypeService {
    
    @Autowired
    private EntityTypeRepository repository;
    
    @Cacheable(value="entitytype", key="123456")// save all in cache until any upload
    public List<AppEntityType> getAll(){
        return (List<AppEntityType>) repository.findAll();
    }
    
    public AppEntityType getById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    @CacheEvict(value="entitytype", key="123456")//clean chache
    public AppEntityType save(AppEntityType entity){
        return repository.save(entity);
    }
    
    @CacheEvict(value="entitytype", key="123456")//clean chache
    public boolean delete(Long id){
        if(getById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
