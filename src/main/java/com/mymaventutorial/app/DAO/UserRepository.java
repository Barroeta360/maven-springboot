/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.DAO;

import com.mymaventutorial.app.Models.AppUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Keton
 */
public interface UserRepository extends JpaRepository<AppUser, Long> {
    
    AppUser findByEmailAndPassword(String email, String password);
    
    List<AppUser> findByUserType(Long userTypeId);
    
}
