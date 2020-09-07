/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.mongodriver.controller;

import com.avbravo.jmoordbcoreweb.mongodriver.microiservices.Fruit;
import com.avbravo.jmoordbcoreweb.mongodriver.microiservices.FruitParam;
import com.avbravo.jmoordbutils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ParamConverterProvider;

/**
 *
 * @author avbravo
 */
@Stateless
public class FruitServices implements Serializable {

    /**
     * Creates a new instance of FruitServices
     */
    public FruitServices() {
    }
    
    
     // <editor-fold defaultstate="collapsed" desc="List<Fruits> findByUsername(String username)">
    public List<Fruit> searchbypathparam(String username) {
        List<Fruit> suggestions = new ArrayList<>();
        try {

          
            Client client = ClientBuilder.newClient();
       
            suggestions = client
                    .target("http://localhost:8080"+ "/jmoordbcoreweb/resources/fruit/searchbypathparam/")
                    .path("/{name}")
                    .resolveTemplate("name", username)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Fruit>>() {
                    });

        } catch (Exception e) {
            
            System.out.println("fsearchbypathparam() " + e.getLocalizedMessage());
            JsfUtil.errorDialog("searchbypathparam()", e.getLocalizedMessage());
        }

        return suggestions;
    }
     // <editor-fold defaultstate="collapsed" desc="List<Fruits> findByUsername(String username)">
    public List<Fruit> filterbyqueryparam(String name, String id) {
        List<Fruit> suggestions = new ArrayList<>();
        try {

            System.out.println("============ Services.filterbyqueryparam==================");
            Client client = ClientBuilder.newClient();
       
            
            suggestions = client
                    .target("http://localhost:8080"+ "/jmoordbcoreweb/resources/fruit/filterbyqueryparam/")
                     .queryParam("name",name )
                     .queryParam("id", id)
                    
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Fruit>>() {});

        } catch (Exception e) {

            JsfUtil.errorDialog(" filterbyqueryparam()", e.getLocalizedMessage());
        }

        return suggestions;
    }
     // <editor-fold defaultstate="collapsed" desc="List<Fruits> findByUsername(String username)">
    public List<Fruit> filterbyqueryparamdate(String name, String id, String date1) {
        List<Fruit> suggestions = new ArrayList<>();
        try {

          
            Client client = ClientBuilder.newClient();
       
            
            suggestions = client
                    .target("http://localhost:8080"+ "/jmoordbcoreweb/resources/fruit/filterbyqueryparamdate/")
                     .queryParam("name",name )
                     .queryParam("id", id)
                     .queryParam("date1", date1)
                    
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Fruit>>() {});

        } catch (Exception e) {

            JsfUtil.errorDialog(" filterbyqueryparam()", e.getLocalizedMessage());
        }

        return suggestions;
    }
     // <editor-fold defaultstate="collapsed" desc="List<Fruits> findByUsername(String username)">
    public List<Fruit> filterbyqueryparamdateParamConverterProvider(String name, String id, Date date1) {
        List<Fruit> suggestions = new ArrayList<>();
        try {
            System.out.println("|**********************************************************");
            System.out.println("|**********************************************************");
            System.out.println("++++++++++++++++++++++++++FruisServices.filterbyqueryparamdateParamConverterProvider()");
           System.out.println("|**********************************************************");
            System.out.println("|******* dete1 es "+date1);
            Client client = ClientBuilder.newClient();
       
            
            suggestions = client
                    .target("http://localhost:8080"+ "/jmoordbcoreweb/resources/fruit/dateparamdateparamconverterprovider/")
                     .queryParam("name",name )
                     .queryParam("id", id)
                     .queryParam("date1", date1)
                    
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Fruit>>() {});

        } catch (Exception e) {
            System.out.println("filterbyqueryparamdateParamConverterProvider() "+e.getLocalizedMessage());
            JsfUtil.errorDialog(" filterbyqueryparamdateParamConverterProvider()", e.getLocalizedMessage());
        }

        return suggestions;
    }
 public List<Fruit> findBeanParam(FruitParam fruitParam){
        List<Fruit> suggestions = new ArrayList<>();
        try {
          
            Client client = ClientBuilder.newClient();
       
            
            suggestions = client
                    .target("http://localhost:8080"+ "/jmoordbcoreweb/resources/fruit/findbeanparamr/")
                     .queryParam("fruitparama",fruitParam )
                                       
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Fruit>>() {});

        } catch (Exception e) {
            System.out.println("findBeanParam(() "+e.getLocalizedMessage());
            JsfUtil.errorDialog("findBeanParam(()", e.getLocalizedMessage());
        }

        return suggestions;
    }
}
