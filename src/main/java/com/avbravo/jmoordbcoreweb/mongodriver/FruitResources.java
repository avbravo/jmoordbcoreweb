/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.mongodriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
  // <editor-fold defaultstate="collapsed" desc="@Path("/searchbypathparam/{name}")">
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
  // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="  @Path("/filterbyqueryparam")">
    @GET
    @Path("/filterbyqueryparam")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fruit> filterbyqueryparam(@QueryParam("name") String name, @QueryParam("id") String id) {
        List<Fruit> fruitList = new ArrayList<>();
        try {
            System.out.println("***************** MICROSERVICES  filterbyqueryparam*****************************");
            System.out.println(">>>>>>>>>> Query param name " + name + " id " + id);
            List<Fruit> list = new ArrayList<>();

            list = fruitRepository.list();
            if (list == null || list.isEmpty()) {
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
    /**
     * Query params con fechas
     *
     * @param name
     * @param id
     * @param date1
     * @return
     */
    @GET
    @Path("/filterbyqueryparamdate")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fruit> filterbyqueryparamdate(@QueryParam("name") String name, @QueryParam("id") String id, @QueryParam("date1") String date1) {
        List<Fruit> fruitList = new ArrayList<>();
        try {
            System.out.println("|------------------------------------------------------------------------------");
            System.out.println("|----------------------------MICROSERVICES  filterbyqueryparamdate--------------------------------");
            Date dateConverter = getDateFromString(date1);//dateString is query param.

            System.out.println("------------------------------------------------");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

            String dateInString = date1;
            Date dateConverterAyer = formatter.parse(dateInString);

            System.out.println("$$$$$$$$============>>> otra fecha con otro converversor" + dateConverterAyer);
            System.out.println("------------------------------------------------");

            System.out.println("|------------------ Query param name " + name + " id " + id + "date1 " + date1 + "Date converter " + dateConverter.toString());
            List<Fruit> list = new ArrayList<>();

            list = fruitRepository.list();
            if (list == null || list.isEmpty()) {
                System.out.println("|--------------lista no tiene ningun elemeento");
            }
            System.out.println("|------------------filtrando datos por fecha convertida......");
            for (Fruit f : list) {
                System.out.println("---------------------------------------------");
                System.out.println("----------comoarando { "+f.getId() + "}");
                System.out.println("------------" + f.getDate1());
                System.out.println("------------" + dateConverter);

          
//                if (f.getId().equals(id)) {
  Integer n=f.getDate1().compareTo(dateConverter);
        System.out.println("------compareTo={"+n+"}");
        
if(f.getDate1().after(dateConverter)){
    System.out.println("--------> after......");
}else{
    if(f.getDate1().before(dateConverter)){
        System.out.println("------> before");
    }else{
      
        
    }
}
                 if (f.getDate1().equals(dateConverter)) {
//                if (f.getDate1().equals(dateConverterAyer)) {
                    fruitList.add(f);
                    System.out.println("------------ fechas iguales....");
                } else {
                    System.out.println("------------ fechas diferentes....");
                }

                System.out.println("......................}");
            }
            System.out.println("|------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("filterbyqueryparam() " + e.getLocalizedMessage());

        }

        return fruitList;
    }

    // </editor-fold>
    private Date getDateFromString(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date conveter = new Date();
        try {
            Date date = df.parse(dateString);

            return date;
        } catch (Exception e) {
            System.out.println("getDateFromString() " + e.getLocalizedMessage());
            //WebApplicationException ...("Date format should be yyyy-MM-dd'T'HH:mm:ssZ", Status.BAD_REQUEST);
        }
        return conveter;
    }
//    private Date getIsoDateFromString(String dateString) {
//      //  DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        
//                 DateTimeFormatter inputFormat = 
//                DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
//
//        // The parsed date
//        final ZonedDateTime parsed = ZonedDateTime.parse(ts, inputFormat);
//
//        // The output format
//        final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String date = outputFormat.format(parsed);
//        
////        
////        Date conveter = new Date();
//        try {
//            Date date = df.parse(dateString);
//
//            return date;
//        } catch (Exception e) {
//            System.out.println("getDateFromString() " + e.getLocalizedMessage());
//            //WebApplicationException ...("Date format should be yyyy-MM-dd'T'HH:mm:ss", Status.BAD_REQUEST);
//        }
//        return conveter;
//    }
}
