/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.DAO;

import com.mymaventutorial.app.Models.AppEntityType;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Keton
 */
public interface EntityTypeRepository extends CrudRepository<AppEntityType, Long> {
    
}
