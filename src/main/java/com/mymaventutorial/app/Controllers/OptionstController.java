/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Keton
 */
@RestController
public class OptionstController {
    
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
