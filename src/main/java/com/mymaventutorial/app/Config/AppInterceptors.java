/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Config;

import com.mymaventutorial.app.Services.EntityMasterService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Keton
 * This works only with AppInterceptorConfig
 */
@Component
public class AppInterceptors implements HandlerInterceptor {
    
    @Autowired
    private EntityMasterService emService;
    
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler) throws Exception{
        
        
        System.out.println("/********************** Pre Handle method is Calling ************/");
        System.out.println(request.getHeader("token"));
        System.out.println("LocalPort: "+request.getLocalPort());
        System.out.println("Protocol: "+request.getProtocol());
        
        
        //litle example of security interceptor
        if("GET".equals(request.getMethod()))
            return true;
        
        Long idUser = new Long(request.getHeader("idUser"));
        if(request.getHeader("idUser") != null && emService.getById(idUser) != null)
            return true;
        //error user id does not exist
        response.setStatus(403);
        return false;
    }
    
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
      Object handler, ModelAndView modelAndView) throws Exception{
        
        System.out.println("/********************** Post Handle method is Calling ************/");
        
        
    }
}
