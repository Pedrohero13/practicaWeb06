/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practicaweb04;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;

/**
 *
 * @author Pedro
 */
public class PracticaWeb04 {

    public static void main(String[] args) {
        IDAOGeneral<Empleado, Integer> daoEmpleado = FactoryDAO.create(FactoryDAO.TYPE.EMPLEADO);
        IDAOGeneral<Departamento, Integer> daoDepartamento = FactoryDAO.create(FactoryDAO.TYPE.DEPARTAMENTO);
        Departamento dep = daoDepartamento.buscarById(1);
        
        Empleado emp = new Empleado();
        emp.setDepartamento(dep);
        emp.setNombre("Ramiro");
        emp.setDireccion("av23");
        emp.setTelefono("328321");
        if (daoEmpleado.guardar(emp)) {
            Logger.getLogger(PracticaWeb04.class.getName()).log(Level.INFO, "Se guardo");

        } else {
            Logger.getLogger(PracticaWeb04.class.getName()).log(Level.WARNING, "Error al guardar");
        }
        for (Empleado lstEmp : daoEmpleado.buscarTodos()) {
            Logger.getLogger(PracticaWeb04.class.getName()).log(Level.INFO, "Nombre: "+  lstEmp.getNombre());

        }

    }
}
