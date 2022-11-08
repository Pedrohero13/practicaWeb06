/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.uv;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author pedro
 */
@Named(value = "calculadoraBean")
@SessionScoped
public class CalculadoraBean implements Serializable {

    private int a;
    private int b;
    
    /**
     * Creates a new instance of CalculadoraBean
     */
    public CalculadoraBean() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    public void sumar (){
        int c = a+b;
        mensaje(String.valueOf(c));
        
    }
    public void mensaje(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado " +msg , msg));
    }
    
}
