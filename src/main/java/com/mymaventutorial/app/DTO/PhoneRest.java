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
public class PhoneRest {
    private Long id;    
    private String code;
    private String prefix;    
    private String phone;    
    private String Description;
    private Long entityMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Long getEntityMaster() {
        return entityMaster;
    }

    public void setEntityMaster(Long entityMaster) {
        this.entityMaster = entityMaster;
    }
}
