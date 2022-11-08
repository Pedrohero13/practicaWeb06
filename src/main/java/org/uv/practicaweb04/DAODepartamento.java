/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practicaweb04;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Pedro
 */
public class DAODepartamento implements IDAOGeneral<Departamento, Integer> {

    @Override
    public boolean guardar(Departamento pojo) {
        HibernateUtil hibernate = HibernateUtil.getInstance();
        TransactionDB<Departamento, Integer> t = new TransactionDB<Departamento, Integer>(pojo, pojo.getClave()) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.save(pojo);
                    session.getTransaction().commit();
                    session.close();
                    return true;
                } catch (HibernateException ex) {
                    if (session != null) {
                        session.getTransaction().rollback();
                    }
                    Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            }
        };
        return hibernate.execute(t);
    }

    @Override
    public boolean modificar(Departamento pojo, Integer id) {
        HibernateUtil hibernate = HibernateUtil.getInstance();
        TransactionDB<Departamento, Integer> t = new TransactionDB<Departamento, Integer>(pojo, pojo.getClave()) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.update(pojo);
                    session.getTransaction().commit();
                    session.close();
                    return true;
                }  catch (HibernateException ex) {
                    if (session != null) {
                        session.getTransaction().rollback();
                    }
                    Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            }
        };
        return hibernate.execute(t);
    }

    @Override
    public boolean eliminar(Integer id) {
        HibernateUtil hibernate = HibernateUtil.getInstance();
        TransactionDB<Departamento, Integer> t = new TransactionDB<Departamento, Integer>(null, id) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                pojo = new Departamento();
                pojo.setClave(id);
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.delete(pojo);
                    session.getTransaction().commit();
                    session.close();
                    return true;
                }  catch (HibernateException ex) {
                    if (session != null) {
                        session.getTransaction().rollback();
                    }
                    Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }

            }
        };
        return hibernate.execute(t);

    }

    @Override
    public Departamento buscarById(Integer id) {
        HibernateUtil hibernate = HibernateUtil.getInstance();
        TransactionDB<Departamento, Integer> t = new TransactionDB<Departamento, Integer>(null, id) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    pojo = session.get(Departamento.class, id);
                    return true;
                }  catch (HibernateException ex) {
                    if (session != null) {
                        session.getTransaction().rollback();
                    }
                    Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }

            }
        };
        if (hibernate.execute(t)) {
            return t.getPojo();
        } else {
            return null;
        }
    }

    @Override
    public List<Departamento> buscarTodos() {
        HibernateUtil hibernate = HibernateUtil.getInstance();
        TransactionDB<Departamento, Integer> t = new TransactionDB<Departamento, Integer>(null, null) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                Session session = null;
                try {
                    lst = new ArrayList<>();
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    Query<Departamento> query = session.createQuery("from Departamento ORDER BY clave");
                    for (Departamento empleado : query.list()) {
                        lst.add(empleado);
                    }
                    return true;
                }  catch (HibernateException ex) {
                    if (session != null) {
                        session.getTransaction().rollback();
                    }
                    Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            }
        };
        if (hibernate.execute(t)) {
            return t.getLst();
        } else {
            return null;
        }
    }

}
