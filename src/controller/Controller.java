/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DbBroker;

/**
 *
 * @author Acer
 */
public class Controller {
    private static Controller instance;
    private DbBroker dbb;
    
    private Controller() {
        dbb = new DbBroker();
    }
    
    public static Controller getInstance(){
        if(instance== null)
            instance = new Controller();
        return instance;
    }
    
    
    
   
}
