/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.DAO;

import com.mymaventutorial.app.Models.AppMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Keton
 */
public interface MessageRepository extends JpaRepository<AppMessage, Long> {
    
    List<AppMessage> findByTo(Long id);
    
}
