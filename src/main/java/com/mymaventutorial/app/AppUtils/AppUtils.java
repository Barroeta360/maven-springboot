/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.AppUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Keton
 */
public class AppUtils {
    /*
    * fills a SetList from Hash<entity> to Hash<id>
    * Object o = entity that contain Hash<entity>
    * String method = method name to invoke and have the Hash<entity> from entity
    */
    public static HashSet SetEntityToRestId(Object o, String method){
        Set<Long> list = new HashSet();
        System.out.println(o.getClass().getName());
        //List of object to catch method return
        Set<Object> objectSet = new HashSet();
        //method to invoke
        Method m;
        try {
            //validate if method exists in class object
            if(validateMethod(o, method)){
                //get method
                m = o.getClass().getMethod(method);
                try {
                    //execute method a catch set result
                    objectSet = (Set<Object>) m.invoke(o);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        //if the invoke method return a result and the resul list is not empty, we add the mapped object to list passed as parameter
        if(objectSet != null && !objectSet.isEmpty())
            //mapping each item from result of invoke method and adding to list passed as parameter
            objectSet.forEach(item -> list.add(getIdFromClass(item)));
        return (HashSet) list;
    }
    
    /*
    * fills a SetList from Hash<entity> to Hash<rest>
    * Object o = entity that contain Hash<entity>
    * String method = method name to invoke and have the Hash<entity> from entity
    * HashSet list = empty list to fill with mapped entity to rest
    * Object service = service that contain the method mapToRest
    */
    public static HashSet SetEntityToRest(Object o, String method, HashSet list, Object service){
        System.out.println(o.getClass().getName());
        //List of object to catch method return
        Set<Object> objectSet = new HashSet();
        //method to invoke
        Method m;
        try {
            //validate if method exists in class object
            if(validateMethod(o, method)){
                //get method
                m = o.getClass().getMethod(method);
                try {
                    //execute method a catch set result
                    objectSet = (Set<Object>) m.invoke(o);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        //if the invoke method return a result and the resul list is not empty, we add the mapped object to list passed as parameter
        if(objectSet != null && !objectSet.isEmpty())
            //mapping each item from result of invoke method and adding to list passed as parameter
            objectSet.forEach(item -> list.add(mapToRest(item, service)));
        return list;
    }
    
    /*
    * Map any entity to rest
    * Object object = object to map to a rest object
    * Object service = service that contain the method mapToRest
    */
    public static Object mapToRest(Object object, Object service){
        Method m;
        Class c = object.getClass();
        if(validateMethod(service, "mapToRest")){
            try {
                m = service.getClass().getDeclaredMethod("mapToRest", c);
                try {
                    return m.invoke(service, ModelMapperUtils.map(object, c));
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public static Long getIdFromClass(Object object){
        Method m;
        if(validateMethod(object, "getId")){
            try {
                m = object.getClass().getMethod("getId");
                try {
                    return (Long) m.invoke(object);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    private static boolean validateMethod(final Object source, String method){
        boolean b = false;
        Method[] methods = source.getClass().getDeclaredMethods();
        for(Method m: methods){
            if(m.getName().equals(method)){
                b = true;
            }
        }
        return b;
    }
    
    
    public static String capitalizeWord(String str) {
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }
}
