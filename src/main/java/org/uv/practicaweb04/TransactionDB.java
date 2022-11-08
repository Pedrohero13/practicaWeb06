/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practicaweb04;

import java.sql.Connection;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author Pedro
 */
public abstract class TransactionDB<T,I> {
    protected T pojo;
    protected I id;
    protected List<T> lst;
    protected  TransactionDB(T pojo, I id) {
        this.pojo = pojo;
        this.id = id;
    }
    
    public abstract boolean  execute (SessionFactory sessionFactory);

    public T getPojo() {
        return pojo;
    }

    public List<T> getLst() {
        return lst;
    }
    
}
