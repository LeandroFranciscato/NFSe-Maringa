/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.evento;

import br.org.abrasf.nfse.TcMensagemRetorno;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesPort;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesService;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.linepack.nfsemaringa.DAO.DAOModelo;
import org.linepack.nfsemaringa.DAO.MensagemDAO;
import org.linepack.nfsemaringa.model.Mensagem;
import org.linepack.nfsemaringa.util.AssinadorXml;
import org.linepack.nfsemaringa.util.Conexao;
import org.xml.sax.SAXException;

/**
 *
 * @author root
 */
public abstract class EventoModelo {

    protected String tagID;
    protected Boolean isSigned;
    protected Object objetoModelo;

    public EventoModelo() {
        this.tagID = "";
        this.isSigned = true;
        this.objetoModelo = new Object();
    }

    protected void run(Conexao conexao) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException, NoSuchMethodException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String xmlEnvio = this.formaXml();
        if (isSigned) {
            xmlEnvio = this.assinaXml(xmlEnvio, tagID, conexao.getNomeCertificadoJKS(), conexao.getSenhaCertificado());
        }
        atualizaXmlOrigem(xmlEnvio, "setXmlEnvio");
        String xmlRetorno = this.enviaXml(xmlEnvio);
        atualizaXmlOrigem(xmlRetorno, "setXmlRetorno");
        this.retornaXml(xmlRetorno);
    }

    protected abstract String formaXml();

    protected String assinaXml(String xml, String tag, String keyStorePath, String password) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException {
        return AssinadorXml.getXmlAssinado(xml, tag, keyStorePath, password);
    }

    protected NfseServicesPort getConnectionPort() {
        return new NfseServicesService().getNfseServicesPort();
    }
    
    protected abstract String enviaXml(String xmlEnvio);

    protected abstract void retornaXml(String xmlRetorno);

    protected void setMensagemRetorno(List<TcMensagemRetorno> mensagens, String eventoGerador, Long idEventoGerador) {
        
        new MensagemDAO().deleteMensagensAnteriores(eventoGerador, idEventoGerador);
        for (TcMensagemRetorno mensagemRetorno : mensagens) {
            Mensagem mensagemModel = new Mensagem();
            mensagemModel.setEventoGerador(eventoGerador);
            mensagemModel.setIdEventoGerador(idEventoGerador);
            mensagemModel.setDataInsercao(new Date());
            mensagemModel.setCodigo(mensagemRetorno.getCodigo());
            mensagemModel.setDescricao(mensagemRetorno.getMensagem());
            mensagemModel.setAcao(mensagemRetorno.getCorrecao());            
            new MensagemDAO().insert(mensagemModel);
        }
    }

    /**
     *
     * @param nomeMetodo must be setXmlEnvio or setXmlRetorno
     */
    protected void atualizaXmlOrigem(String xml, String nomeMetodo) throws NoSuchMethodException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method metodo = this.objetoModelo.getClass().getMethod(nomeMetodo, String.class);
        metodo.invoke(this.objetoModelo, xml);
        DAOModelo daoModelo = new DAOModelo();
        daoModelo.update(this.objetoModelo);
    }        

}
