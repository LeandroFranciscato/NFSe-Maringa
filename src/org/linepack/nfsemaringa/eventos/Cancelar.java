/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.eventos;

import br.org.abrasf.nfse.CancelarNfseEnvio;
import br.org.abrasf.nfse.CancelarNfseResposta;
import br.org.abrasf.nfse.TcCpfCnpj;
import br.org.abrasf.nfse.TcIdentificacaoNfse;
import br.org.abrasf.nfse.TcInfPedidoCancelamento;
import br.org.abrasf.nfse.TcMensagemRetorno;
import br.org.abrasf.nfse.TcPedidoCancelamento;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesPort;
import https.isseteste_maringa_pr_gov_br.ws.NfseServicesService;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.linepack.nfsemaringa.DAO.CancelamentoDAO;
import org.linepack.nfsemaringa.model.Cancelamento;
import org.linepack.nfsemaringa.util.MarshallerUtil;
import org.linepack.nfsemaringa.util.UnmarshallerUtil;
import org.xml.sax.SAXException;

/**
 *
 * @author root
 */
public class Cancelar extends EventoModelo {

    public Cancelar() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException {
        CancelamentoDAO cancelamentoDAO = new CancelamentoDAO();
        for (Cancelamento cancelamento : cancelamentoDAO.getCancelamentosPendentes()) {
            this.tagID = "InfPedidoCancelamento";    
            this.objetoModelo = cancelamento;            
            super.run();
        }
    }

    @Override
    public String formacaoXml(Object objeto) {

        Cancelamento cancelamento = (Cancelamento) objeto;

        TcInfPedidoCancelamento tipc = new TcInfPedidoCancelamento();
        tipc.setCodigoCancelamento(new Integer(1).byteValue());
        tipc.setId(cancelamento.getId().toString());

        TcIdentificacaoNfse tin = new TcIdentificacaoNfse();
        tin.setCodigoMunicipio(cancelamento.getCodigoMunicipio());
        tin.setInscricaoMunicipal(cancelamento.getInscricaoMunicipal());
        TcCpfCnpj cnpj = new TcCpfCnpj();
        cnpj.setCnpj(cancelamento.getCnpj());
        tin.setCpfCnpj(cnpj);
        tin.setNumero(cancelamento.getNumeroNota());

        tipc.setIdentificacaoNfse(tin);

        TcPedidoCancelamento tpc = new TcPedidoCancelamento();
        tpc.setInfPedidoCancelamento(tipc);

        CancelarNfseEnvio cancelarNfseEnvio = new CancelarNfseEnvio();
        cancelarNfseEnvio.setPedido(tpc);

        String xml = null;
        try {
            xml = MarshallerUtil.marshal(CancelarNfseEnvio.class, cancelarNfseEnvio);
        } catch (JAXBException ex) {
            Logger.getLogger(Cancelar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xml;
    }

    @Override
    public String envioXml(String xmlEnvio) {
        return this.getConnectionPort().cancelarNfse(xmlEnvio);
    }

    @Override
    public void retornoXml(String xmlRetorno) {
        try {
            CancelarNfseResposta cneResposta = (CancelarNfseResposta) UnmarshallerUtil.unmarshal(CancelarNfseResposta.class, xmlRetorno);
            for (TcMensagemRetorno msgRetorno : cneResposta.getListaMensagemRetorno().getMensagemRetorno()) {
                System.out.println(msgRetorno.getMensagem());
            }
        } catch (JAXBException ex) {
            Logger.getLogger(Cancelar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
