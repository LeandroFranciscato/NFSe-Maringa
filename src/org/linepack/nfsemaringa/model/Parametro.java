package org.linepack.nfsemaringa.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author leandro
 */
@Entity
@NamedQuery(name = "parametroAtivo", query = "select p from Parametro p where p.isAtivo = 1")
public class Parametro implements Serializable {

    @Id
    private Long id;
    private String nomeCertificadoJKS;
    private String senhaCertificado;
    private Integer isAtivo;

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

    public Integer getIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(Integer isAtivo) {
        this.isAtivo = isAtivo;
    }

}
