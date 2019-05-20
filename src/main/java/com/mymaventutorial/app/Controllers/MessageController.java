/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Controllers;

import com.mymaventutorial.app.DTO.IdRest;
import com.mymaventutorial.app.DTO.MessageRest;
import com.mymaventutorial.app.Models.AppMessage;
import com.mymaventutorial.app.Services.MessageService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Keton
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    
    @Autowired
    private MessageService service;
    
    @GetMapping()
    public List<Object> list() {
        return null;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        List<AppMessage> entity = service.getByReceiver(id);
        return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody MessageRest input) {
        AppMessage entity = service.mapFromRest(input);
        entity= service.save(entity);
        if(entity != null)
            return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.CREATED);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object cannot be created");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
