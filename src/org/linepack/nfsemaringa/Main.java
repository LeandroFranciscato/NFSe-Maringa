/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa;

import br.org.abrasf.nfse.CancelarNfseEnvio;
import br.org.abrasf.nfse.CancelarNfseResposta;
import br.org.abrasf.nfse.TcMensagemRetorno;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesPort;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import org.linepack.nfsemaringa.util.Conexao;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.linepack.nfsemaringa.util.AssinadorXml;
import org.linepack.nfsemaringa.util.MarshallerUtil;
import org.linepack.nfsemaringa.util.UnmarshallerUtil;
import org.xml.sax.SAXException;

/**
 *
 * @author root
 */
public class Main {

    public static void main(String[] args) throws JAXBException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException {

        Conexao.certifica();           
        CancelarNfseEnvio cne = new CancelarNfse().criaCancelamento();
        String xmlEnvio = MarshallerUtil.marshal(CancelarNfseEnvio.class, cne);
                         
        //debug********************************************************************
        xmlEnvio = AssinadorXml.getXmlAssinado(xmlEnvio,CancelarNfse.tagID , "CERTIFICADO.jks", "");
        OutputStream os = new FileOutputStream(new File("xmlEnvio.xml"));
        os.write(xmlEnvio.getBytes());

        // ENVIA AO WEB SERVICE
        NfseServicesPort port = new NfseServicesService().getNfseServicesPort();
        String xmlRetorno = port.cancelarNfse(xmlEnvio);
        
        //debug*********************************************************************
        OutputStream osRetorno = new FileOutputStream(new File("xmlRetorno.xml"));
        osRetorno.write(xmlRetorno.getBytes());
        
        CancelarNfseResposta cneResposta = (CancelarNfseResposta) UnmarshallerUtil.unmarshal(CancelarNfseResposta.class, xmlRetorno);
            
        for (TcMensagemRetorno msgRetorno : cneResposta.getListaMensagemRetorno().getMensagemRetorno()){
            System.out.println(msgRetorno.getMensagem());            
        }
        
    }

}
