package org.linepack.nfsemaringa.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author leandro
 */
@Entity
public class Parametros implements Serializable {

    @Id
    private Long id;
    private String nomeCertificadoJKS;
    private String senhaCertificado;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCertificadoJKS() {
        return nomeCertificadoJKS;
    }

    public void setNomeCertificadoJKS(String nomeCertificadoJKS) {
        this.nomeCertificadoJKS = nomeCertificadoJKS;
    }

    public String getSenhaCertificado() {
        return senhaCertificado;
    }

    public void setSenhaCertificado(String senhaCertificado) {
        this.senhaCertificado = senhaCertificado;
    }
    
    
    
    
   
}
