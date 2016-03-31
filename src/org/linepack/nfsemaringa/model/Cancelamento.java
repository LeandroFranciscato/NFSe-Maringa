/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author root
 */
@Entity
@NamedQuery(name = "cancelamentosPendentes", query = "select c from Cancelamento c where c.isCancelada = 0")
public class Cancelamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigInteger numeroNota;
    private String cnpj;
    private String inscricaoMunicipal;
    private Integer codigoMunicipio;
    private Integer isCancelada;
    private String usuarioInsercao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataInsercao;
    private String usuarioAlteracao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @Column(columnDefinition = "CLOB")
    private String xmlEnvio;
    @Column(columnDefinition = "CLOB")
    private String xmlRetorno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(BigInteger numeroNota) {
        this.numeroNota = numeroNota;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Integer getIsCancelada() {
        return isCancelada;
    }

    public void setIsCancelada(Integer isCancelada) {
        this.isCancelada = isCancelada;
    }

    public String getUsuarioInsercao() {
        return usuarioInsercao;
    }

    public void setUsuarioInsercao(String usuarioInsercao) {
        this.usuarioInsercao = usuarioInsercao;
    }

    public Date getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(Date dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getXmlEnvio() {
        return xmlEnvio;
    }

    public void setXmlEnvio(String xmlEnvio) {
        this.xmlEnvio = xmlEnvio;
    }

    public String getXmlRetorno() {
        return xmlRetorno;
    }

    public void setXmlRetorno(String xmlRetorno) {
        this.xmlRetorno = xmlRetorno;
    }

}
