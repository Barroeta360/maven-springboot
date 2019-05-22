/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author KETON
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
    
    @Temporal(TIMESTAMP)
    @Column(nullable=false, updatable=false)
    protected Date creationDate;

    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;

    public Date getCreationDate() {
        return creationDate;
    }

    @PrePersist
    public void onCreationDate() {
        this.creationDate = new Date();
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    @PreUpdate
    public void setLastModifiedDate() {
        this.lastModifiedDate = new Date();
    }
}
