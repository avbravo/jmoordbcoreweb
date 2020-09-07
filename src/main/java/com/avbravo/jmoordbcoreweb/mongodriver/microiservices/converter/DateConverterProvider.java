/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.mongodriver.microiservices.converter;

import java.time.LocalDate;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

/**
 *
 * @author avbravo
 */
public class DateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> type, java.lang.reflect.Type type1, java.lang.annotation.Annotation[] antns) {
       if (type1.equals(LocalDate.class))
            return (ParamConverter<T>) new MyDateConverter();
        return null;
    }
}
