/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.mongodriver.services;

import java.util.Date;
import javax.ws.rs.QueryParam;

/**
 *
 * @author avbravo
 */
public class FruitParam {
    @QueryParam("name")
       private String name;
    @QueryParam("description")
    private String description;
     @QueryParam("id")
    private String id;
      @QueryParam("date1")
    private Date date1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }
    
    
    
    
}
