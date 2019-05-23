/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.DAO.UserRepository;
import com.mymaventutorial.app.Models.AppUser;
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
public class UserService {
    
    @Autowired UserRepository repository;
    
    @Cacheable(value="users", key="123456")// save all in cache until any upload
    public List<AppUser> getAll(){
        return repository.findAll();
    }
    
    public AppUser login(String email, String password){
        return repository.findByEmailAndPassword(email, password);
    }
    
    public AppUser getById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    public List<AppUser> getByTypeId(Long id){
        return repository.findByUserType(id);
    }
    
    public AppUser getByEntityMaster(Long id){
        return repository.findByEntityMaster(id);
    }
    
    @CacheEvict(value="users", key="123456")//clean chache
    public AppUser save(AppUser entity){
        return repository.save(entity);
    }
    
    @CacheEvict(value="users", key="123456")//clean chache
    public boolean delete(Long id){
        if(repository.findById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
    
}
