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

    public DAOModelo() {
        this.entityManager = new EntityManagerUtil().getEntityManager();
    }

    public String insert(T object) {
        if (object != null) {
            entityManager.persist(object);
            entityManager.getTransaction().commit();
            entityManager.close();
            return null;
        }
        return "Erro ao inserir, objeto Nulo.";
    }

    public String update(T object) {
        if (object != null) {
            entityManager.merge(object);
            entityManager.getTransaction().commit();
            entityManager.close();
            return null;
        }
        return "Erro ao atualizar, objeto Nulo.";
    }

    public T getByID(String modelClassName, Integer id) {
        Query query = this.entityManager.createQuery(""
                + "select a"
                + "  from " + modelClassName + " a "
                + " where a.id = " + id);
        Object object = new Object();
        object = query.getSingleResult();
        this.entityManager.close();
        return (T) object;
    }

    public T getByNamedQuery(String namedQueryName) {
        Query query = this.entityManager.createNamedQuery(namedQueryName);
        Object object = new Object();
        object = query.getSingleResult();
        return (T) object;

    }

    public <T> List<T> getListByNamedQuery(String namedQueryName) throws IllegalAccessException {
        Query query = this.entityManager.createNamedQuery(namedQueryName);
        List objectList = new ArrayList<>();
        objectList = query.getResultList();
        this.entityManager.close();
        return objectList;
    }
}
