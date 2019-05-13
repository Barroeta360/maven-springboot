/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Controllers;

import com.mymaventutorial.app.DTO.CountryRest;
import com.mymaventutorial.app.Models.AppCountry;
import com.mymaventutorial.app.Services.CountryService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Keton
 */
@RestController
@RequestMapping("/country")
public class CountryController {
    
    @Autowired
    private CountryService service;
    
    @GetMapping()
    public @ResponseBody List<CountryRest> list() {
        return service.mapToRest(service.getAll());
    }
    
    @GetMapping("/{id}")
    public @ResponseBody CountryRest get(@PathVariable Long id) {
        return service.mapToRest(service.getById(id));
    }
    
    @GetMapping("/name/{id}")
    public @ResponseBody CountryRest get(@PathVariable String name) {
        return service.mapToRest(service.getByName(name));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody CountryRest input) {
        if(input.getId() != null && !Objects.equals(id, input.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id object is not equals to pathvariable id");
        
        AppCountry entity = service.mapFromRest(input);
        entity = service.save(entity);
        if(entity != null)
            return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object cannot be updated");
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody CountryRest input) {
        AppCountry entity = service.mapFromRest(input);
        if(service.getByName(input.getName()) == null)
            entity = service.save(entity);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The country with name: " + input.getName() + " already exists");
        
        if(entity != null)
            return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.CREATED);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object cannot be updated");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(service.delete(id))
            return new ResponseEntity<>(null, HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object with id: " +id+ " doesn´t exists");
        
    }
    /*
    @DeleteMapping("/error")
    public ResponseEntity<?> error(@PathVariable Long id) {
        return null;
    }
    /*
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }*/
    
}
