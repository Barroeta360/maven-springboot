/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.DAO;

import com.mymaventutorial.app.Models.AppDniType;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Keton
 */
public interface DniTypeRepository extends CrudRepository<AppDniType, Long> {
    
}
