/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.navigator;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author avbravo
 */
@Named(value = "navigatorController")
@SessionScoped
public class NavigatorController implements Serializable {

    /**
     * Creates a new instance of NavigatorController
     */
    public NavigatorController() {
    }
     public String go(String pathPage){
        return pathPage;
    }
}
