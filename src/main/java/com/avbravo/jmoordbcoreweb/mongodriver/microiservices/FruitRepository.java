/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.mongodriver.microiservices;

import com.avbravo.jmoordbutils.JsfUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.bson.Document;

/**
 *
 * @author avbravo
 */

@Stateless
public class FruitRepository implements Serializable{
  
    private com.mongodb.MongoClient mongoClient = null;

      @PostConstruct
    public void init() {
        try {
            /* String database="";
            String username=""; 
            String password="";
            String host="localhost"; 
            String port="27017"; 
            
            char[] charArray = password.toCharArray(); 
            MongoCredential credential = MongoCredential.createCredential(username, database, charArray);
            mongoClient = new MongoClient(new ServerAddress(host,port), Arrays.asList(credential));
            */ 
            mongoClient = new com.mongodb.MongoClient();
        } catch (Exception e) {
            JsfUtil.errorMessage("init() " + e.getLocalizedMessage());
        }
    }
    public FruitRepository() {
 //mongoClient = MongoClients.create(JmoordbCodecNative.settings());
   // mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }
    public List<Fruit> list(){
      
        List<Fruit> list = new ArrayList<>();
      
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
               
                Document document = cursor.next();
               
                Fruit fruit = new Fruit();
                fruit.setName(document.getString("name"));
                fruit.setId(document.getString("id"));
                fruit.setDescription(document.getString("description"));
                fruit.setDate1(document.getDate("date1"));
                list.add(fruit);
               
            }
            
        }catch(Exception ex){
            System.out.println("list() "+ex.getLocalizedMessage());
        } finally {
            cursor.close();
        }
      
        return list;
    }
    public List<Fruit> findByDate(Date date){
      
        List<Fruit> list = new ArrayList<>();
      
        MongoCursor<Document> cursor = getCollection().find(new Document("date1",date)).iterator();
        try {
            while (cursor.hasNext()) {               
                Document document = cursor.next();
               
                Fruit fruit = new Fruit();
                fruit.setName(document.getString("name"));
                fruit.setId(document.getString("id"));
                fruit.setDescription(document.getString("description"));
                fruit.setDate1(document.getDate("date1"));
                list.add(fruit);
               
            }
            
        }catch(Exception ex){
            System.out.println("list() "+ex.getLocalizedMessage());
        } finally {
            cursor.close();
        }
      
        return list;
    }

    public void add(Fruit fruit){
        Document document = new Document()
                .append("name", fruit.getName())
                .append("id", fruit.getId())
                
                .append("description", fruit.getDescription())
                .append("date1", fruit.getDate1());
        getCollection().insertOne(document);
    }

    private MongoCollection getCollection(){
     
         return mongoClient.getDatabase("jmoordbcore").getCollection("fruit");
    }
    /**
     * Creates a new instance of FruitServices
     */

    
}
