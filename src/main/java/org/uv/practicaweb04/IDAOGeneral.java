package org.uv.practicaweb04;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


import java.util.List;

/**
 *
 * @author Pedro
 */
public interface IDAOGeneral<T,I>{
    public boolean guardar(T pojo);
    public boolean modificar (T pojo, I id);
    public boolean eliminar (I id);
    public T buscarById(I id);
    public List<T> buscarTodos();
    
    
}
