/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.DAO;

import javax.persistence.Query;

/**
 *
 * @author root
 */
public class MensagemDAO extends DAOModelo {

    public MensagemDAO() {
        super();
    }

    public void deleteMensagensAnteriores(String eventoGerador, Long idEventoGerador) {
        try {
            Query query = super.entityManager.createQuery(""
                    + "delete from Mensagem m "
                    + " where m.eventoGerador = '" + eventoGerador + "'"
                    + "   and m.idEventoGerador = " + idEventoGerador);
            query.executeUpdate();
            super.entityManager.getTransaction().commit();
            super.entityManager.close();
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }

}
