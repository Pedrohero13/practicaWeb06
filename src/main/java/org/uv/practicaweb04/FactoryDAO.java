/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practicaweb04;

/**
 *
 * @author Pedro
 */
public class FactoryDAO {

    public enum TYPE {EMPLEADO, DEPARTAMENTO}

    public static IDAOGeneral create(TYPE type) {
        IDAOGeneral dao = null;
        switch (type) {
            case DEPARTAMENTO:
                dao = new DAODepartamento();
                break;
            case EMPLEADO:
                dao = new DAOEmpleado();
                break;
        }
        return dao;

    }
}
