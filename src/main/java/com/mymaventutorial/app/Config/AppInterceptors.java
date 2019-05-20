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
 * @author Keton This works only with AppInterceptorConfig
 */
@Component
public class AppInterceptors implements HandlerInterceptor {

    @Autowired
    private EntityMasterService emService;

    public static final String REQUEST_ORIGIN_NAME = "Origin";

    public static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
    public static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
    public static final String METHODS_NAME = "Access-Control-Allow-Methods";
    public static final String HEADERS_NAME = "Access-Control-Allow-Headers";
    public static final String MAX_AGE_NAME = "Access-Control-Max-Age";

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        System.out.println("/********************** Pre Handle method is Calling ************/");
        System.out.println(request.getHeader("token"));
        System.out.println("LocalPort: " + request.getLocalPort());
        System.out.println("Protocol: " + request.getProtocol());
        System.out.println("Handler - " + handler.toString());
        
        //Hard validate METHOD.OPTIONS 
        if ("OPTIONS".equals(request.getMethod())) {
            System.out.println("/********************** OPTIONS ************/");
            response.setHeader(CREDENTIALS_NAME, "true");
            response.setHeader(METHODS_NAME, "GET, OPTIONS, POST, PUT, DELETE");
            response.setHeader(HEADERS_NAME, "Origin, X-Requested-With, Content-Type, Accept");
            response.setHeader(MAX_AGE_NAME, "3600");
        }

        //litle example of security interceptor
        //avoid any validation to method GET
        if ("GET".equals(request.getMethod()) || "OPTIONS".equals(request.getMethod())) {
            return true;
        }
        
        //validate header and iduser is a valid user
        Long idUser;
        if(request.getHeader("idUser") != null){
            idUser = new Long(request.getHeader("idUser"));
            if (emService.getById(idUser) != null) {
                return true;
            }
        }
            
        //error user id does not exist or no header present in request
        response.setStatus(403);
        return false;//remeber to change it 
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("/********************** Post Handle method is Calling ************/");

    }
}
