/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa;

import java.io.IOException;
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
import org.linepack.nfsemaringa.util.AssinadorXml;
import org.xml.sax.SAXException;

/**
 *
 * @author root
 */
public class Main {

    public static void main(String[] args) throws JAXBException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException {
                        
        Conexao.certifica();
        
        AssinadorXml.assina();
        /*
        // ATRIBUI OS DADOS
        ConsultarNfseFaixaEnvio cnfe = new ConsultarNfseFaixaEnvio();
        ConsultarNfseFaixaEnvio.Faixa faixa = new ConsultarNfseFaixaEnvio.Faixa();
        TcIdentificacaoPrestador prestador = new TcIdentificacaoPrestador();

        faixa.setNumeroNfseInicial(BigInteger.valueOf(42));
        faixa.setNumeroNfseFinal(BigInteger.valueOf(42));

        TcCpfCnpj cnpj = new TcCpfCnpj();
        cnpj.setCnpj("72042799000190");
        prestador.setCpfCnpj(cnpj);
        prestador.setInscricaoMunicipal("054170");

        cnfe.setFaixa(faixa);
        cnfe.setPagina(1);
        cnfe.setPrestador(prestador);
        
        // OBJETO TO XML
        JAXBContext context = JAXBContext.newInstance(ConsultarNfseFaixaEnvio.class);
        Marshaller m = context.createMarshaller();        
        Writer writer = new CharArrayWriter();
        m.marshal(cnfe, writer);
                               
        // ENVIA AO WEB SERVICE
        NfseServicesPort port = new NfseServicesService().getNfseServicesPort();        
        String xml = port.consultarNfseFaixa(writer.toString());
        
        //XML TO OBJETO
        JAXBContext contextRetorno =  JAXBContext.newInstance(ConsultarNfseFaixaResposta.class);
        Unmarshaller unmarshaller =  contextRetorno.createUnmarshaller();
        Reader reader =  new CharArrayReader(xml.toCharArray());
        ConsultarNfseFaixaResposta cnfeResposta = (ConsultarNfseFaixaResposta) unmarshaller.unmarshal(reader);
        
        System.out.println(cnfeResposta.getListaNfse().getCompNfse().get(0).getNfse().getInfNfse().getNumero());
        */
    }

}
