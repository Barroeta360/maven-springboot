/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.DAO.UserTypeRepository;
import com.mymaventutorial.app.Models.AppUserType;
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
public class UserTypeService {
    
    @Autowired
    private UserTypeRepository repository;
    
    @Cacheable(value="usertype", key="123456")// save all in cache until any upload
    public List<AppUserType> getAll(){
        return (List<AppUserType>) repository.findAll();
    }
    
    public AppUserType getById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    @CacheEvict(value="usertype", key="123456")//clean chache
    public AppUserType save(AppUserType entity){
        return repository.save(entity);
    }
    
    @CacheEvict(value="usertype", key="123456")//clean chache
    public boolean delete(Long id){
        if(getById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
