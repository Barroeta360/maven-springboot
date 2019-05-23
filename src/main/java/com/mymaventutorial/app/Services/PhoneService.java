/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.DAO.PhoneRepository;
import com.mymaventutorial.app.Models.AppPhone;
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
public class PhoneService {
    
    @Autowired
    private PhoneRepository repository;
    
    @Cacheable(value="phone", key="123456")// save all in cache until any upload
    public List<AppPhone> getAll(){
        return repository.findAll();
    }
    
    public List<AppPhone> getByEntityMaster(Long id){
        return repository.findByEntityMaster(id);
    }
    
    @CacheEvict(value="phone", key="123456")//clean chache
    public AppPhone save(AppPhone entity){
        return repository.save(entity);
    }
    
    @CacheEvict(value="phone", key="123456")//clean chache
    public boolean delete(Long id){
        if(repository.findById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
