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
import org.linepack.nfsemaringa.model.Cancelamento;
import org.linepack.nfsemaringa.util.EntityManagerUtil;

/**
 *
 * @author root
 */
public class CancelamentoDAO {

    private final EntityManager entityManager;
    
    public CancelamentoDAO(){
        this.entityManager = new EntityManagerUtil().getEntityManager();
    }
           
    public List<Cancelamento> getCancelamentosPendentes() {             
        Query query = this.entityManager.createQuery(""
                + "select c "
                + "  from Cancelamento c "
                + " where c.isCancelada = 0");        
        
        List<Cancelamento> cancelamentos = new ArrayList<>();
        cancelamentos = (List<Cancelamento>) query.getResultList();
        this.entityManager.close();
        return cancelamentos;
    }

}
