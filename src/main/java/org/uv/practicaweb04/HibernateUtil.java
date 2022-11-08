/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practicaweb04;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.spi.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Pedro
 */
public class HibernateUtil {
    private static HibernateUtil hibernate = null;
    private SessionFactory sessionFactory= null;
    
    public static HibernateUtil getInstance(){
        if(hibernate == null){
            hibernate = new HibernateUtil();
           
        }
        return hibernate;
    } 
    

    private HibernateUtil() {
     try {
            StandardServiceRegistry standardRegistry
                    = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metaData
                    = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
        } catch (Throwable th) {
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, "ERROR {0}", th.getMessage());
        }
    }
    
    
    public boolean execute (TransactionDB t){
        return t.execute(sessionFactory);
    }
}
