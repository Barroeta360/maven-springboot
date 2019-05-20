/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.DTO;

import java.util.Date;

/**
 *
 * @author Keton
 */
public class MessageRest  {

    private Long id;
    
    private Long from;
    
    private Long to;
    
    private Date messageDate;
    
    private String message;
    
    private boolean updated;

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public MessageRest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Date getDate() {
        return messageDate;
    }

    public void setDate(Date date) {
        this.messageDate = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
