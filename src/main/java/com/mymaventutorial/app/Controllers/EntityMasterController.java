/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Controllers;

import com.mymaventutorial.app.DTO.EntityMasterRest;
import com.mymaventutorial.app.Models.AppEntityMaster;
import com.mymaventutorial.app.Services.AppMailService;
import com.mymaventutorial.app.Services.EntityMasterService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private EntityMasterService service;

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping()
    public ResponseEntity<?> get(@RequestParam(defaultValue = "0", required = false) Long id) {
        if (!id.equals(0)) {
            return new ResponseEntity<>(service.mapToRest(service.getAll()), HttpStatus.OK);
        } else {
            AppEntityMaster entity = service.getById(id);
            if (entity != null) {
                return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object with id: " + id + " doesn´t exists");
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        AppEntityMaster entity = service.getById(id);
        if (entity != null) {
            return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object with id: " + id + " doesn´t exists");
        }
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody EntityMasterRest input) {
        AppEntityMaster entity = service.mapFromRest(input);
        entity = service.save(entity);
        if (entity != null) {
            AppMailService mail = new AppMailService(emailSender);
            if (mail.sendEmail(entity.getName(), "Congrtulations!!!", "Your account has been created!! \n\n\n Att: MavenWebApp")) {
                //update status
            }
            return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object cannot be created");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody EntityMasterRest input) {
        if (input.getId() != null && !Objects.equals(id, input.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id object is not equals to pathvariable id");
        }
        AppEntityMaster entity = service.mapFromRest(input);
        entity = service.save(entity);
        if (entity != null) {
            return new ResponseEntity<>(service.mapToRest(entity), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object cannot be updated");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object with id: " + id + " doesn´t exists");
        }
    }
    /*
    @RequestMapping(
            method = RequestMethod.OPTIONS,
            value = "/*"
    )
    public ResponseEntity options(HttpServletResponse response) {
        response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS,POST,DELETE");
        return new ResponseEntity(HttpStatus.OK);
    }*/
}
