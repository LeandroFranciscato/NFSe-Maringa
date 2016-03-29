/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa;

import br.org.abrasf.nfse.CancelarNfseEnvio;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesPort;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesService;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.xml.bind.JAXBContext;
import org.linepack.nfsemaringa.util.Conexao;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
public class Main {

    public static void main(String[] args) throws JAXBException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException {

        Conexao.certifica();           
        CancelarNfseEnvio cne = new CancelarNfse().criaCancelamento();

        // OBJETO TO XML
        JAXBContext context = JAXBContext.newInstance(CancelarNfseEnvio.class);
        Marshaller m = context.createMarshaller();        
        Writer writer = new CharArrayWriter();
        m.marshal(cne, writer);
                         
        //debug
        String xmlEnvio = AssinadorXml.getXmlAssinado(writer.toString(),CancelarNfse.tagID , "CERTIFICADO.jks", "");
        OutputStream os = new FileOutputStream(new File("xmlEnvio.xml"));
        os.write(xmlEnvio.getBytes());

        // ENVIA AO WEB SERVICE
        NfseServicesPort port = new NfseServicesService().getNfseServicesPort();
        String xmlRetorno = port.cancelarNfse(xmlEnvio);
        
        //debug
        OutputStream osRetorno = new FileOutputStream(new File("xmlRetorno.xml"));
        osRetorno.write(xmlRetorno.getBytes());

        /*        
        //XML TO OBJETO
        JAXBContext contextRetorno =  JAXBContext.newInstance(ConsultarNfseFaixaResposta.class);
        Unmarshaller unmarshaller =  contextRetorno.createUnmarshaller();
        Reader reader =  new CharArrayReader(xml.toCharArray());
        ConsultarNfseFaixaResposta cnfeResposta = (ConsultarNfseFaixaResposta) unmarshaller.unmarshal(reader);
        
        System.out.println(cnfeResposta.getListaNfse().getCompNfse().get(0).getNfse().getInfNfse().getNumero());
         */
    }

}
