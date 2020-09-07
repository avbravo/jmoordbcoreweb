/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.mongodriver.microiservices.converter;

import com.avbravo.jmoordbutils.DateUtil;
import java.time.LocalDate;
import java.util.Date;
import javax.ws.rs.ext.ParamConverter;

/**
 *
 * @author avbravo
 */
public class MyDateConverter implements ParamConverter<Date> {

    @Override
    public Date fromString(String value) {
        if (value == null)
            return null;
        return DateUtil.stringToISODate(value);
    }

    @Override
    public String toString(Date value) {
        if (value == null)
            return null;
        return value.toString();
    }

   

    
}
