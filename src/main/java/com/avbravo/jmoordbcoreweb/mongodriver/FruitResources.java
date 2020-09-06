/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.mongodriver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.bson.Document;

/**
 *
 * @author avbravo
 */
@Path("fruit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResources {

    @Inject
    FruitRepository fruitRepository;

    @GET
    public List<Fruit> list() {

        return fruitRepository.list();
    }

    // <editor-fold defaultstate="collapsed" desc="@Path("/findall")">
    @GET
    @Path("/findall")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fruit> findAll() {
        return fruitRepository.list();
    }
    // </editor-fold>

    @POST
    public List<Fruit> add(Fruit fruit) {
        fruitRepository.add(fruit);
        return list();
    }

    @GET
    @Path("/searchbypathparam/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> searchbypathparam(@PathParam("name") String name) {
        List<Fruit> fruitList = new ArrayList<>();

        try {
            System.out.println("***************** MICROSERVICES  searchbypathparam(*****************************");
            List<Fruit> list = new ArrayList<>();

            list = fruitRepository.list();
            for (Fruit f : list) {
                if (f.getName().equals(name)) {
                    fruitList.add(f);
                }
            }

        } catch (Exception e) {
            System.out.println("searchbypathparam() " + e.getLocalizedMessage());

        }

        return fruitList;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="@Path("/filterbyusername/{username}") ">
    @GET
    @Path("/filterbyqueryparam")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fruit> filterbyqueryparam(@QueryParam("name") String name, @QueryParam("id") String id) {
        List<Fruit> fruitList = new ArrayList<>();
        try {            
            System.out.println("***************** MICROSERVICES  filterbyqueryparam*****************************");
            System.out.println(">>>>>>>>>> Query param name "+name + " id "+id);
            List<Fruit> list = new ArrayList<>();

            list = fruitRepository.list();
            if(list == null || list.isEmpty()){
                System.out.println("...... lista no tiene ningun elemeento");
            }
            System.out.println("..........filtrando datos......");
            for (Fruit f : list) {
                if (f.getId().equals(id)) {
                    fruitList.add(f);
                    System.out.println("add.......");
                }
            }

        } catch (Exception e) {
            System.out.println("filterbyqueryparam() " + e.getLocalizedMessage());

        }

        return fruitList;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Path("/filterbyqueryparamdate") ">
    @GET
    @Path("/filterbyqueryparamdate")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fruit> filterbyqueryparamdate(@QueryParam("name") String name, @QueryParam("id") String id, @QueryParam("date1") Date date1) {
        List<Fruit> fruitList = new ArrayList<>();
        try {            
            System.out.println("***************** MICROSERVICES  filterbyqueryparam*****************************");
            System.out.println(">>>>>>>>>> Query param name "+name + " id "+id + "date1 "+date1);
            List<Fruit> list = new ArrayList<>();

            list = fruitRepository.list();
            if(list == null || list.isEmpty()){
                System.out.println("...... lista no tiene ningun elemeento");
            }
            System.out.println("..........filtrando datos......");
            for (Fruit f : list) {
                if (f.getId().equals(id)) {
                    fruitList.add(f);
                    System.out.println("add.......");
                }
            }

        } catch (Exception e) {
            System.out.println("filterbyqueryparam() " + e.getLocalizedMessage());

        }

        return fruitList;
    }

    // </editor-fold>
    

}