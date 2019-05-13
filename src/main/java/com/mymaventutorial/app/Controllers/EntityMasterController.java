/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Controllers;

import com.mymaventutorial.app.DTO.EntityMasterRest;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Keton
 */
@RestController
@RequestMapping("/entitymaster")
public class EntityMasterController {
    
    /*@GetMapping()
    public List<Object> list() {
        return null;
    }*/
    
    
    
    
    @GetMapping()
    public @ResponseBody List<EntityMasterRest> get(@RequestParam(defaultValue="Maximo", required=false) String name ) {
        ArrayList<EntityMasterRest> list = new ArrayList<>();
        EntityMasterRest rest = new EntityMasterRest();
        rest.setId(new Long(5));
        rest.setName(name);
        list.add(rest);
        return list;
    }
    
    @PostMapping()
    public @ResponseBody List<EntityMasterRest> post(@RequestBody EntityMasterRest input) {
        ArrayList<EntityMasterRest> list = new ArrayList<>();
        input.setId(new Long(3));
        list.add(input);
        return list;
    }
    
    
    /*
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Object input) {
        return null;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    */
}
