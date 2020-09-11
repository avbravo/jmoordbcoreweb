/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbcoreweb.security;

import com.avbravo.jmoordbutils.JsfUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class AccessController implements Serializable {
private String name;
   private String path = "/jmoordbcoreweb/faces/login.xhtml?faces-redirect=true";
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


 @PostConstruct
    public void init() {
        name="Aristides Villarreal";
    }
    // <editor-fold defaultstate="collapsed" desc="String logout(String path)">
    public String logout() {
        Boolean loggedIn = false;
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
            String url = (path);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect(url);
            return path;
        } catch (Exception e) {
          JsfUtil.errorDialog("logout() ", e.getLocalizedMessage());
          
        }
        return path;
    }
// </editor-fold>

}
