/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.DTO;

/**
 *
 * @author Keton
 */
public class UserRest {
    
    private Long id;    
    private String email;    
    private Long entityMaster;
    private Long userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEntityMaster() {
        return entityMaster;
    }

    public void setEntityMaster(Long entityMaster) {
        this.entityMaster = entityMaster;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }
    
    
}
