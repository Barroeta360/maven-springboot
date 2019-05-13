/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.DAO;

import com.mymaventutorial.app.Models.AppCountry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Keton
 */
public interface CountryRepository extends JpaRepository<AppCountry, Long> {
    
    AppCountry findByName(String name);
    
}
