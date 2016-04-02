/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.DAO;

import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.linepack.nfsemaringa.util.EntityManagerUtil;

/**
 *
 * @author root
 */
public class DAOModelo {

    public EntityManager entityManager;

    public DAOModelo() {
        this.entityManager = new EntityManagerUtil().getEntityManager();
    }

    public void insert(Object object) {
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Object object) {
        entityManager.merge(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Object getByID(String modelClassName, Integer id) {
        Query query = this.entityManager.createQuery(""
                + "select a"
                + "  from " + modelClassName + " a "
                + " where a.id = " + id);
        Object object = new Object();
        object = query.getSingleResult();
        this.entityManager.close();
        return object;

    }

    public List getListByNamedQuery(String namedQueryName) {
        Query query = this.entityManager.createNamedQuery(namedQueryName);
        List objectList = new ArrayList<>();
        objectList = (List) query.getResultList();
        this.entityManager.close();
        return objectList;
    }   

}
