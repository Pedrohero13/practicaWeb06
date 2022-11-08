/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.uv;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.uv.practicaweb04.DAOEmpleado;
import org.uv.practicaweb04.Departamento;
import org.uv.practicaweb04.Empleado;

/**
 *
 * @author pedro
 */
@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

    private int clave;
    private String nombre;
    private String direccion;
    private String telefono;

    private Empleado selectEmpleado;
    private List<Empleado> empleados;
    private DAOEmpleado dao = new DAOEmpleado();

    /**
     * Creates a new instance of EmpleadoBean
     */
    public EmpleadoBean() {
    }

    public Empleado getSelectEmpleado() {
        return selectEmpleado;
    }

    public void setSelectEmpleado(Empleado selectEmpleado) {
        this.selectEmpleado = selectEmpleado;
    }

    public DAOEmpleado getDao() {
        return dao;
    }

    public void setDao(DAOEmpleado dao) {
        this.dao = dao;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public void guardar() {

        Empleado persis = new Empleado();
        persis.setNombre(nombre);
        persis.setTelefono(telefono);
        persis.setDireccion(direccion);

        if (dao.guardar(persis)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado guardado"));
            nombre = "";
            telefono = "";
            direccion = "";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al guardar"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

    }

    @PostConstruct
    public void init() {
        this.empleados = this.dao.buscarTodos();
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void modificar() {
        Empleado updateEmp = new Empleado();
        updateEmp.setClave(selectEmpleado.getClave());
        updateEmp.setNombre(selectEmpleado.getNombre());
        updateEmp.setDireccion(selectEmpleado.getDireccion());
        updateEmp.setTelefono(selectEmpleado.getTelefono());
        Departamento dep = new Departamento();
        dep.setClave(1);
        updateEmp.setDepartamento(dep);

        if (dao.modificar(updateEmp, clave)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Empleado Modificado"));

            this.selectEmpleado = null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al modificar"));

        }
        PrimeFaces.current().executeScript("PF('updateDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

    }

    public void eliminar() {
        if (dao.eliminar(selectEmpleado.getClave())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Empleado eliminado"));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al eliminar"));

        }

        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void buscar() {
        Empleado aux = dao.buscarById(clave);
        if (aux != null) {
            this.selectEmpleado = aux;
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Empleado no encontrado"));
            

            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

        }
        
        

    }
}
