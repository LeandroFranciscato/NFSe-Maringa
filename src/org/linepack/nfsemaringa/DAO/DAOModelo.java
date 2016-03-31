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
public class DAOModelo {
    
    private final EntityManager entityManager;
    
    public DAOModelo(){
        this.entityManager = new EntityManagerUtil().getEntityManager();
    }
    
    public void insert(Object object){
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
        
    public Object getByID(String modelClassName, Integer id){
        Query query = this.entityManager.createQuery(""
                + "select a"
                + "  from "+modelClassName+""
                + " where id = "+id);                
        Object object = new Object();
        object =  query.getResultList();        
        this.entityManager.close();
        return object;
        
    };
    
    public List getList(String stmt){
        Query query = this.entityManager.createQuery(stmt);                
        List objectList = new ArrayList<>();
        objectList = (List) query.getResultList();        
        this.entityManager.close();        
        return objectList;
    }
    
}
