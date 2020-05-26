/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb;

import com.avbravo.jmoordbcore.annotations.core.CodecNativeClient;
import com.avbravo.jmoordbcore.codecnative.CodecNative;
import com.avbravo.jmoordbcore.codecnative.JmoordbCodecNative;
import com.avbravo.jmoordbcore.codecnative.example.Calle;
import com.avbravo.jmoordbcore.codecnative.example.Person;
import com.avbravo.jmoordbcore.codecnative.example.Address;
import com.avbravo.jmoordbutils.JsfUtil;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.bson.types.ObjectId;

/**
 *
 * @author avbravo
 */
@Named(value = "coreController")
@SessionScoped
public class CoreController implements Serializable {
// <editor-fold defaultstate="collapsed" desc="environment">

    @Inject
    @CodecNativeClient
    MongoClient mongoClientCodecNative;

    //Definir codecNative
    //MongoClient mongoClient = MongoClients.create(JmoordbCodecNative.settings());
//    final CodecNative codecNative = JmoordbCodecNative.createConnection(mongoClient, "jmoordb_nativecode");
    CodecNative codecNative;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="entity()">
    private Person person = new Person();
    private Address address = new Address();
    private Calle calle = new Calle();

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="get/set()">
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Calle getCalle() {
        return calle;
    }

    public void setCalle(Calle calle) {
        this.calle = calle;
    }

// </editor-fold>
    /**
     * Creates a new instance of CoreController
     */
    public CoreController() {
    }

    @PostConstruct
    public void init() {
        try {
            if (mongoClientCodecNative == null) {
                System.out.println("+++++++++++++Es null");
            } else {
                System.out.println("++++++++++++++No es Null");
                codecNative = JmoordbCodecNative.createConnection(mongoClientCodecNative, "jmoordb_nativecode");
            }
        } catch (Exception e) {
        }

    }
// <editor-fold defaultstate="collapsed" desc="save()">

    public String save() {
        try {

//       Person person = new Person("Ada Byron", 20, new Address("St James Square", "London", "W1", new Calle("c", "Rosio")));
            address.setCalle(calle);
            person.setAddress(address);
            person.setId(ObjectId.get());
            codecNative.save(person);
            JsfUtil.infoDialog("save()", "Guardado");

        } catch (Exception e) {
            System.out.println("save() " + e.getLocalizedMessage());
            JsfUtil.errorDialog("savee()", e.getLocalizedMessage());
        }
        return "";
    }
    // </editor-fold>
}
