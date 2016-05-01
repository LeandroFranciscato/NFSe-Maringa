/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.linepack.nfsemaringa.util.EntityManagerUtil;

/**
 *
 * @author root
 */
public class DAOModelo<T> {

    protected EntityManager entityManager;
    private Class<T> classe;

    public DAOModelo() {
        this.entityManager = new EntityManagerUtil().getEntityManager();
    }

    public void insert(T object) {
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(T object) {
        entityManager.merge(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public T getByID(String modelClassName, Integer id) {
        Query query = this.entityManager.createQuery(""
                + "select a"
                + "  from " + modelClassName + " a "
                + " where a.id = " + id, classe);
        Object object = new Object();
        object = query.getSingleResult();
        this.entityManager.close();
        return classe.cast(object);
    }

    public T getByNamedQuery(String namedQueryName) {
        Query query = this.entityManager.createNamedQuery(namedQueryName, classe);
        if (query.getFirstResult() == 0) {
            return null;
        }
        Object object = new Object();
        object = query.getSingleResult();
        return classe.cast(object);
    }

    public <T> List<T> getListByNamedQuery(String namedQueryName) throws IllegalAccessException {
        Query query = this.entityManager.createNamedQuery(namedQueryName, classe);
        List objectList = new ArrayList<>();
        objectList = query.getResultList();
        this.entityManager.close();
        return objectList;
    }
}
