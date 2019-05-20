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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Keton
 */
@RestController
public class SocketController {
    
    @Autowired
    private MessageService msgService;
    
    @MessageMapping("/newmessage")//to create a new message
    @SendTo("/topic/getmessage")//te get the message list
    public ResponseEntity<?> get(@RequestBody MessageRest input) throws InterruptedException {
        
        AppMessage message = msgService.mapFromRest(input);
        message = msgService.save(message);
        if(message == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The object cannot be created");
        Thread.sleep(1000);
        List<AppMessage> entity = msgService.getByReceiver(input.getTo());
        return new ResponseEntity<>(msgService.mapToRest(entity), HttpStatus.OK);
    }
}
