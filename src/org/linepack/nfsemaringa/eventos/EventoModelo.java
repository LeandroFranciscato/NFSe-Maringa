/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.eventos;

import https.isseteste_maringa_pr_gov_br.ws.NfseServicesPort;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesService;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.linepack.nfsemaringa.util.AssinadorXml;
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
        String xmlEnvio = this.formacaoXml(objetoModelo);
        if (isSigned) {
            xmlEnvio = this.assinaXml(xmlEnvio, tagID, "CERTIFICADO.jks", "");
        }
        String xmlRetorno = this.envioXml(xmlEnvio);
        this.retornoXml(xmlRetorno);
    }

    protected abstract String formacaoXml(Object objetoModelo);

    protected String assinaXml(String xml, String tag, String keyStorePath, String password) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException {
        return AssinadorXml.getXmlAssinado(xml, tag, keyStorePath, password);
    }

    protected NfseServicesPort getConnectionPort() {
        return new NfseServicesService().getNfseServicesPort();
    }

    protected abstract String envioXml(String xmlEnvio);

    protected abstract void retornoXml(String XmlRetorno);

}
