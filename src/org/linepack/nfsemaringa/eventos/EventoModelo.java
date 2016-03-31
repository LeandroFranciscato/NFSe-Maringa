/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.eventos;

import br.org.abrasf.nfse.TcMensagemRetorno;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesPort;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesService;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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

    protected void run() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException {
        Conexao.certifica();
        String xmlEnvio = this.formacaoXml(objetoModelo);
        if (isSigned) {
            xmlEnvio = this.assinaXml(xmlEnvio, tagID, "CERTIFICADO.jks", "");
        }
        String xmlRetorno = this.envioXml(xmlEnvio);
        this.retornoXml(xmlRetorno, objetoModelo);
    }

    protected abstract String formacaoXml(Object objetoModelo);

    protected String assinaXml(String xml, String tag, String keyStorePath, String password) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException {
        return AssinadorXml.getXmlAssinado(xml, tag, keyStorePath, password);
    }

    protected NfseServicesPort getConnectionPort() {
        return new NfseServicesService().getNfseServicesPort();
    }

    protected abstract String envioXml(String xmlEnvio);

    protected abstract void retornoXml(String XmlRetorno, Object objetoModelo);

    protected void setMensagemRetorno(List<TcMensagemRetorno> mensagens, String eventoGerador, Long idEventoGerador) {
        for (TcMensagemRetorno mensagemRetorno : mensagens) {
            Mensagem mensagemModel = new Mensagem();
            mensagemModel.setEventoGerador(eventoGerador);
            mensagemModel.setIdEventoGerador(idEventoGerador);
            mensagemModel.setDataInsercao(new Date());
            mensagemModel.setCodigo(mensagemRetorno.getCodigo());
            mensagemModel.setDescricao(mensagemRetorno.getMensagem());
            mensagemModel.setAcao(mensagemRetorno.getCorrecao());
            MensagemDAO mensagemDAO = new MensagemDAO();
            mensagemDAO.insert(mensagemModel);
        }
    }
}
