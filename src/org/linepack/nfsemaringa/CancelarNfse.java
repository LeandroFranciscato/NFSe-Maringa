/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa;

import br.org.abrasf.nfse.CancelarNfseEnvio;
import br.org.abrasf.nfse.TcCpfCnpj;
import br.org.abrasf.nfse.TcIdentificacaoNfse;
import br.org.abrasf.nfse.TcInfPedidoCancelamento;
import br.org.abrasf.nfse.TcPedidoCancelamento;
import java.io.IOException;
import java.math.BigInteger;
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
public class CancelarNfse {
    
    protected static final String tagID = "InfPedidoCancelamento";
    
    public CancelarNfseEnvio criaCancelamento() throws NoSuchAlgorithmException, JAXBException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException{                
        
        TcInfPedidoCancelamento tipc = new TcInfPedidoCancelamento();
        tipc.setCodigoCancelamento(new Integer(1).byteValue());
        tipc.setId("1");           
        
        TcIdentificacaoNfse tin = new TcIdentificacaoNfse();
        tin.setCodigoMunicipio(41);
        tin.setInscricaoMunicipal("054170");
        TcCpfCnpj cnpj = new TcCpfCnpj();
        cnpj.setCnpj("72042799000190");
        tin.setCpfCnpj(cnpj);
        tin.setNumero(BigInteger.valueOf(42));
              
        tipc.setIdentificacaoNfse(tin);        
        
        TcPedidoCancelamento tpc = new TcPedidoCancelamento();        
        tpc.setInfPedidoCancelamento(tipc);            

        CancelarNfseEnvio cancelarNfseEnvio = new CancelarNfseEnvio();
        cancelarNfseEnvio.setPedido(tpc);
        
        return cancelarNfseEnvio;
    }
    
}
