/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Controllers;

import com.mymaventutorial.app.AppUtils.ModelMapperUtils;
import com.mymaventutorial.app.DTO.StateRest;
import com.mymaventutorial.app.Models.AppCountry;
import com.mymaventutorial.app.Models.AppState;
import com.mymaventutorial.app.Services.CountryService;
import com.mymaventutorial.app.Services.StateService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/state")
public class StateController {
    
    @Autowired
    private StateService service;
    
    @Autowired
    private CountryService countryService;
    
    @GetMapping()
    public ResponseEntity<?> list() {
        List<StateRest> rest = service.mapToRest(service.getAll());
        return new ResponseEntity<>(rest, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return null;
    }
    
    @GetMapping("/country/{id}")
    public ResponseEntity<?> getByCountry(@PathVariable Long id) {
        AppCountry country = countryService.getById(id);
        if(country == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The country with id: " + id + " does not exists");
        
        List<StateRest> rest = service.mapToRest(service.getByCountry(country));
        return new ResponseEntity<>(rest, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody StateRest input) {
        AppState entity = service.mapFromRest(input);
        entity = service.save(entity);
        if(entity != null)
            return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object cannot be created");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
