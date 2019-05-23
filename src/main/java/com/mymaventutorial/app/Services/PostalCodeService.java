/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;

import com.mymaventutorial.app.DAO.PostalCodeRepository;
import com.mymaventutorial.app.Models.AppPostalCode;
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
public class PostalCodeService {
    
    @Autowired
    private PostalCodeRepository repository;
    
    @Cacheable(value="postalcode", key="123456")// save all in cache until any upload
    public List<AppPostalCode> getAll(){
        return repository.findAll();
    }
    
    public AppPostalCode getBypostalConde(String postalCode){
        return repository.findByPostalCode(postalCode);
    }
    
    @CacheEvict(value="postalcode", key="123456")//clean chache
    public AppPostalCode save(AppPostalCode entity){
        return repository.save(entity);
    }
    
    @CacheEvict(value="postalcode", key="123456")//clean chache
    public boolean delete(Long id){
        if(repository.findById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
