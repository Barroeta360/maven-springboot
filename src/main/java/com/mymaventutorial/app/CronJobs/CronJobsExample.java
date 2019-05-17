/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mymaventutorial.app.CronJobs;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Keton
 */
@Component
//@EnableScheduling
public class CronJobsExample {
    
    @Scheduled(cron = "0 28 1 ? * *")//todos los dias del mes, todos los meses, a la 1 y 28 de la mañana
    public void printCron(){
        System.err.println("/*\n*\n*\n*\n*\n*\n*\n\t\t\tCron works\n*\n*\n*\n*\n*\n*\n*/");
    }
    
    @Scheduled(cron = "5 17 5 ? * *")
    public void printCron2(){
        System.err.println("/*\n*\n*\n*\n*\n*\n*\n\t\t\tCron works in 5\n*\n*\n*\n*\n*\n*\n*/");
    }
    
}
