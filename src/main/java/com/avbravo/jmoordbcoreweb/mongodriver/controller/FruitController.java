/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.mongodriver.controller;

import com.avbravo.jmoordbcoreweb.mongodriver.Fruit;
import com.avbravo.jmoordbcoreweb.mongodriver.services.FruitServices;
import com.avbravo.jmoordbutils.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Named(value = "fruitController")
@SessionScoped
public class FruitController implements Serializable {

    List<Fruit> fruitList = new ArrayList<>();
    @Inject
    FruitServices fruitServices;

    public List<Fruit> getFruitList() {
        return fruitList;
    }

    public void setFruitList(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    /**
     * Creates a new instance of FruitController
     */
    public FruitController() {
    }

    @PostConstruct
    public void init() {
        fruitList = new ArrayList<>();
    }

    public String searchbypathparam() {
        List<Fruit> suggestions = new ArrayList<>();
        try {
            fruitList = new ArrayList<>();
            System.out.println("==============searchbypathparam()=================");
            suggestions = fruitServices.searchbypathparam("apple");
            if (suggestions == null || suggestions.isEmpty()) {
                System.out.println("No econtrpo ");
                JsfUtil.warningDialog("Fruits", "no encontro");
            } else {
                fruitList = suggestions;
                JsfUtil.infoDialog("Fruits", "Encontrados");
                for (Fruit f : suggestions) {
                    System.out.println("------------------ " + f.getName() + "" + f.getId() + " " + f.getDate1());
                }
            }
        } catch (Exception e) {

            JsfUtil.errorDialog("searchbypathparam()", e.getLocalizedMessage());
        }

        return "";
    }

    public String filterbyqueryparam() {
        List<Fruit> suggestions = new ArrayList<>();
        try {
            fruitList = new ArrayList<>();
            System.out.println("==============filterbyqueryparam()=================");
            suggestions = fruitServices.filterbyqueryparam("orange", "2");
            if (suggestions == null || suggestions.isEmpty()) {
                System.out.println("No econtrpo ");
                JsfUtil.warningDialog("Fruits", "no encontro");
            } else {
                fruitList = suggestions;
                JsfUtil.infoDialog("Fruits", "Encontrados");
                for (Fruit f : suggestions) {
                    System.out.println("----------------- " + f.getName() + "" + f.getId() + " " + f.getDate1());
                }
            }
        } catch (Exception e) {

            JsfUtil.errorDialog("filterbyqueryparam() ", e.getLocalizedMessage());
        }

        return "";
    }

    public String filterbyqueryparamdate() {
        List<Fruit> suggestions = new ArrayList<>();

        try {
      
            String date1 = "";
            List<Fruit> searchList = fruitServices.searchbypathparam("apple");
            if (searchList == null || searchList.isEmpty()) {
                System.out.println("------------------------> no hay apple");
            } else {
                Fruit f = searchList.get(0);
                System.out.println("---- voy a llamar a isoDateToString()");
                date1 = isoDateToString(f.getDate1());
                System.out.println("---------------------------> encontre apple con date =>" + f.getId() + " date" + f.getDate1() + " --->>--" + date1);

            }
            fruitList = new ArrayList<>();

//            suggestions = fruitServices.filterbyqueryparamdate("orange", "2", new Date().toString());
            suggestions = fruitServices.filterbyqueryparamdate("orange", "2", date1);
            if (suggestions == null || suggestions.isEmpty()) {
                System.out.println("No econtrpo ");
                JsfUtil.warningDialog("Fruits", "no encontro");
            } else {
                fruitList = suggestions;
                JsfUtil.infoDialog("Fruits", "Encontrados");
                for (Fruit f : suggestions) {
                    System.out.println("----------------- " + f.getName() + "" + f.getId() + " " + f.getDate1());
                }
            }
        } catch (Exception e) {

            JsfUtil.errorDialog("filterbyqueryparam() ", e.getLocalizedMessage());
        }

        return "";
    }

    private String isoDateToString(Date date) {
        try {
            SimpleDateFormat sdf;
//sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.Z");
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//sdf.setTimeZone(TimeZone.getTimeZone("CET"));
            sdf.setTimeZone(TimeZone.getDefault());
            String text = sdf.format(date);
            return text;
        } catch (Exception e) {
            JsfUtil.errorDialog("isoDateToString() ", e.getLocalizedMessage());
        }
        return "";

    }

}
