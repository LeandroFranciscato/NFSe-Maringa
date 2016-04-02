/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
@NamedQuery(name = "enviosPendentes", query = "select e from Envio e where e.isEnviada = 0 and e.isProblematica = 0")
public class Envio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RPS            
    private Integer numeroRps;
    private String serieRps;
    private String tipoRps;
    @Temporal(TemporalType.DATE)
    private Date dataEmissaoRps;
    private Integer statusRps;
    @Temporal(TemporalType.DATE)
    private Date dataCompetenciaRps;

    // SERVIÇO
    private BigDecimal valorServicos;
    private BigDecimal valorDeducoes;
    private BigDecimal valorPis;
    private BigDecimal valorCofins;
    private BigDecimal valorInss;
    private BigDecimal valorIr;
    private BigDecimal valorCsll;
    private BigDecimal outrasRetencoes;
    private BigDecimal valorIss;
    private BigDecimal aliquota;
    private BigDecimal descontoIncondicionado;
    private BigDecimal descontoCondicionado;
    private Integer issRetido;
    private Integer responsavelRetencao;
    private String itemListaServico;
    private Integer codigoCnae;
    private String codigoTributacaoMunicipio;
    private String discriminacaoServico;
    private Integer codigoMunicipioServico;
    private String codigoPais;
    private Integer exigibilidadeIss;
    private Integer municipioIncidencia;
    private String numeroProcesso;

    // PRESTADOR
    private String tipoDocumentoPrestador;
    private String numeroCnpjCpfPrestador;
    private String inscricaoMunicipalPrestador;

    // TOMADOR
    private String tipoDocumentoTomador;
    private String numeroCnpjCpfTomador;
    private String inscricaoMunicipalTomador;
    private String razaoSocialTomador;
    private String enderecoTomador;
    private String numeroEnderecoTomador;
    private String complementoEnderecoTomador;
    private String bairroTomador;
    private Integer codigoMunicipioTomador;
    private String ufTomador;
    private Integer codigoPaisTomador;
    private String cepTomador;
    private String telefoneTomador;
    private String emailTomador;

    //CONSTRUÇÃO CIVIL
    private String codigoObra;
    private String artObra;

    // OUTRAS INFORMAÇÕES
    private Integer regimeEspecialTributacao;
    private Integer optanteSimplesNacional;
    private Integer incentivoFiscal;
    private Integer isEnviada;
    private BigInteger numeroNfse;
    private Integer isProblematica;

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

    public Integer getNumeroRps() {
        return numeroRps;
    }

    public void setNumeroRps(Integer numeroRps) {
        this.numeroRps = numeroRps;
    }

    public String getSerieRps() {
        return serieRps;
    }

    public void setSerieRps(String serieRps) {
        this.serieRps = serieRps;
    }

    public String getTipoRps() {
        return tipoRps;
    }

    public void setTipoRps(String tipoRps) {
        this.tipoRps = tipoRps;
    }

    public Date getDataEmissaoRps() {
        return dataEmissaoRps;
    }

    public void setDataEmissaoRps(Date dataEmissaoRps) {
        this.dataEmissaoRps = dataEmissaoRps;
    }

    public Integer getStatusRps() {
        return statusRps;
    }

    public void setStatusRps(Integer statusRps) {
        this.statusRps = statusRps;
    }

    public Date getDataCompetenciaRps() {
        return dataCompetenciaRps;
    }

    public void setDataCompetenciaRps(Date dataCompetenciaRps) {
        this.dataCompetenciaRps = dataCompetenciaRps;
    }

    public BigDecimal getValorServicos() {
        return valorServicos;
    }

    public void setValorServicos(BigDecimal valorServicos) {
        this.valorServicos = valorServicos;
    }

    public BigDecimal getValorDeducoes() {
        return valorDeducoes;
    }

    public void setValorDeducoes(BigDecimal valorDeducoes) {
        this.valorDeducoes = valorDeducoes;
    }

    public BigDecimal getValorPis() {
        return valorPis;
    }

    public void setValorPis(BigDecimal valorPis) {
        this.valorPis = valorPis;
    }

    public BigDecimal getValorCofins() {
        return valorCofins;
    }

    public void setValorCofins(BigDecimal valorCofins) {
        this.valorCofins = valorCofins;
    }

    public BigDecimal getValorInss() {
        return valorInss;
    }

    public void setValorInss(BigDecimal valorInss) {
        this.valorInss = valorInss;
    }

    public BigDecimal getValorIr() {
        return valorIr;
    }

    public void setValorIr(BigDecimal valorIr) {
        this.valorIr = valorIr;
    }

    public BigDecimal getValorCsll() {
        return valorCsll;
    }

    public void setValorCsll(BigDecimal valorCsll) {
        this.valorCsll = valorCsll;
    }

    public BigDecimal getOutrasRetencoes() {
        return outrasRetencoes;
    }

    public void setOutrasRetencoes(BigDecimal outrasRetencoes) {
        this.outrasRetencoes = outrasRetencoes;
    }

    public BigDecimal getValorIss() {
        return valorIss;
    }

    public void setValorIss(BigDecimal valorIss) {
        this.valorIss = valorIss;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getDescontoIncondicionado() {
        return descontoIncondicionado;
    }

    public void setDescontoIncondicionado(BigDecimal descontoIncondicionado) {
        this.descontoIncondicionado = descontoIncondicionado;
    }

    public BigDecimal getDescontoCondicionado() {
        return descontoCondicionado;
    }

    public void setDescontoCondicionado(BigDecimal descontoCondicionado) {
        this.descontoCondicionado = descontoCondicionado;
    }

    public Integer getIssRetido() {
        return issRetido;
    }

    public void setIssRetido(Integer issRetido) {
        this.issRetido = issRetido;
    }

    public Integer getResponsavelRetencao() {
        return responsavelRetencao;
    }

    public void setResponsavelRetencao(Integer responsavelRetencao) {
        this.responsavelRetencao = responsavelRetencao;
    }

    public String getItemListaServico() {
        return itemListaServico;
    }

    public void setItemListaServico(String itemListaServico) {
        this.itemListaServico = itemListaServico;
    }

    public Integer getCodigoCnae() {
        return codigoCnae;
    }

    public void setCodigoCnae(Integer codigoCnae) {
        this.codigoCnae = codigoCnae;
    }

    public String getCodigoTributacaoMunicipio() {
        return codigoTributacaoMunicipio;
    }

    public void setCodigoTributacaoMunicipio(String codigoTributacaoMunicipio) {
        this.codigoTributacaoMunicipio = codigoTributacaoMunicipio;
    }

    public String getDiscriminacaoServico() {
        return discriminacaoServico;
    }

    public void setDiscriminacaoServico(String discriminacaoServico) {
        this.discriminacaoServico = discriminacaoServico;
    }

    public Integer getCodigoMunicipioServico() {
        return codigoMunicipioServico;
    }

    public void setCodigoMunicipioServico(Integer codigoMunicipioServico) {
        this.codigoMunicipioServico = codigoMunicipioServico;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Integer getExigibilidadeIss() {
        return exigibilidadeIss;
    }

    public void setExigibilidadeIss(Integer exigibilidadeIss) {
        this.exigibilidadeIss = exigibilidadeIss;
    }

    public Integer getMunicipioIncidencia() {
        return municipioIncidencia;
    }

    public void setMunicipioIncidencia(Integer municipioIncidencia) {
        this.municipioIncidencia = municipioIncidencia;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getTipoDocumentoPrestador() {
        return tipoDocumentoPrestador;
    }

    public void setTipoDocumentoPrestador(String tipoDocumentoPrestador) {
        this.tipoDocumentoPrestador = tipoDocumentoPrestador;
    }

    public String getNumeroCnpjCpfPrestador() {
        return numeroCnpjCpfPrestador;
    }

    public void setNumeroCnpjCpfPrestador(String numeroCnpjCpfPrestador) {
        this.numeroCnpjCpfPrestador = numeroCnpjCpfPrestador;
    }

    public String getInscricaoMunicipalPrestador() {
        return inscricaoMunicipalPrestador;
    }

    public void setInscricaoMunicipalPrestador(String inscricaoMunicipalPrestador) {
        this.inscricaoMunicipalPrestador = inscricaoMunicipalPrestador;
    }

    public String getTipoDocumentoTomador() {
        return tipoDocumentoTomador;
    }

    public void setTipoDocumentoTomador(String tipoDocumentoTomador) {
        this.tipoDocumentoTomador = tipoDocumentoTomador;
    }

    public String getNumeroCnpjCpfTomador() {
        return numeroCnpjCpfTomador;
    }

    public void setNumeroCnpjCpfTomador(String numeroCnpjCpfTomador) {
        this.numeroCnpjCpfTomador = numeroCnpjCpfTomador;
    }

    public String getInscricaoMunicipalTomador() {
        return inscricaoMunicipalTomador;
    }

    public void setInscricaoMunicipalTomador(String inscricaoMunicipalTomador) {
        this.inscricaoMunicipalTomador = inscricaoMunicipalTomador;
    }

    public String getRazaoSocialTomador() {
        return razaoSocialTomador;
    }

    public void setRazaoSocialTomador(String razaoSocialTomador) {
        this.razaoSocialTomador = razaoSocialTomador;
    }

    public String getEnderecoTomador() {
        return enderecoTomador;
    }

    public void setEnderecoTomador(String enderecoTomador) {
        this.enderecoTomador = enderecoTomador;
    }

    public String getNumeroEnderecoTomador() {
        return numeroEnderecoTomador;
    }

    public void setNumeroEnderecoTomador(String numeroEnderecoTomador) {
        this.numeroEnderecoTomador = numeroEnderecoTomador;
    }

    public String getComplementoEnderecoTomador() {
        return complementoEnderecoTomador;
    }

    public void setComplementoEnderecoTomador(String complementoEnderecoTomador) {
        this.complementoEnderecoTomador = complementoEnderecoTomador;
    }

    public String getBairroTomador() {
        return bairroTomador;
    }

    public void setBairroTomador(String bairroTomador) {
        this.bairroTomador = bairroTomador;
    }

    public Integer getCodigoMunicipioTomador() {
        return codigoMunicipioTomador;
    }

    public void setCodigoMunicipioTomador(Integer codigoMunicipioTomador) {
        this.codigoMunicipioTomador = codigoMunicipioTomador;
    }

    public String getUfTomador() {
        return ufTomador;
    }

    public void setUfTomador(String ufTomador) {
        this.ufTomador = ufTomador;
    }

    public Integer getCodigoPaisTomador() {
        return codigoPaisTomador;
    }

    public void setCodigoPaisTomador(Integer codigoPaisTomador) {
        this.codigoPaisTomador = codigoPaisTomador;
    }

    public String getCepTomador() {
        return cepTomador;
    }

    public void setCepTomador(String cepTomador) {
        this.cepTomador = cepTomador;
    }

    public String getTelefoneTomador() {
        return telefoneTomador;
    }

    public void setTelefoneTomador(String telefoneTomador) {
        this.telefoneTomador = telefoneTomador;
    }

    public String getEmailTomador() {
        return emailTomador;
    }

    public void setEmailTomador(String emailTomador) {
        this.emailTomador = emailTomador;
    }

    public String getCodigoObra() {
        return codigoObra;
    }

    public void setCodigoObra(String codigoObra) {
        this.codigoObra = codigoObra;
    }

    public String getArtObra() {
        return artObra;
    }

    public void setArtObra(String artObra) {
        this.artObra = artObra;
    }

    public Integer getRegimeEspecialTributacao() {
        return regimeEspecialTributacao;
    }

    public void setRegimeEspecialTributacao(Integer regimeEspecialTributacao) {
        this.regimeEspecialTributacao = regimeEspecialTributacao;
    }

    public Integer getOptanteSimplesNacional() {
        return optanteSimplesNacional;
    }

    public void setOptanteSimplesNacional(Integer optanteSimplesNacional) {
        this.optanteSimplesNacional = optanteSimplesNacional;
    }

    public Integer getIncentivoFiscal() {
        return incentivoFiscal;
    }

    public void setIncentivoFiscal(Integer incentivoFiscal) {
        this.incentivoFiscal = incentivoFiscal;
    }

    public Integer getIsEnviada() {
        return isEnviada;
    }

    public void setIsEnviada(Integer isEnviada) {
        this.isEnviada = isEnviada;
    }

    public BigInteger getNumeroNfse() {
        return numeroNfse;
    }

    public void setNumeroNfse(BigInteger numeroNfse) {
        this.numeroNfse = numeroNfse;
    }

    public Integer getIsProblematica() {
        return isProblematica;
    }

    public void setIsProblematica(Integer isProblematica) {
        this.isProblematica = isProblematica;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Envio)) {
            return false;
        }
        Envio other = (Envio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.linepack.nfsemaringa.model.Envio[ id=" + id + " ]";
    }

}
