/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.jsonp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import static javax.json.stream.JsonParser.Event.VALUE_STRING;

/**
 *
 * @author avbravo
 */
@Named(value = "jsonP")
@SessionScoped
public class JsonP implements Serializable {

    /**
     * Creates a new instance of JsonP
     */
    public JsonP() {
    }

    public String create() {
   String filter="{"
           + "{\"field\":\"username\",\"op\":\"eq\",\"value\":\"avbravo\"  }"
           +"{\"field\":\"esatadounidad\",\"op\":\"eq\",\"value\":\"solicitado\"  }"          
           +"}";
                
             String result1= "{\"name\":\"Falco\",\"age\":3,\"bitable\":false}";
            
        JsonObject json = Json.createObjectBuilder()
                .add("name", "Falco")
                .add("age", BigDecimal.valueOf(3))
                .add("biteable", Boolean.FALSE).build();
        String result = json.toString();

        //Procesar
        final JsonParser parser = Json.createParser(new StringReader(result));
        String key = null;
        String value = null;
        while (parser.hasNext()) {
            final Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    key = parser.getString();
                    System.out.println(key);
                    break;
                case VALUE_STRING:
                    String string = parser.getString();
                    System.out.println(string);
                    break;
                case VALUE_NUMBER:
                    BigDecimal number = parser.getBigDecimal();
                    System.out.println(number);
                    break;
                case VALUE_TRUE:
                    System.out.println(true);
                    break;
                case VALUE_FALSE:
                    System.out.println(false);
                    break;
                case VALUE_NULL:
                    System.out.println(false);
                    break;

            }
        }
        parser.close();

        return "";
    }

    public String arrays() {
        var ab = Json.createArrayBuilder();
        var ob = Json.createObjectBuilder();
        ob.add("field", "username");
        ob.add("value", "aristides.villarreal");
        ab.add(ob);
        
        ob.add("operator","and");
        ob.add("field", "estadounidad");
        ob.add("value", "solicitado");
        ab.add(ob);

        return "";
    }
}
