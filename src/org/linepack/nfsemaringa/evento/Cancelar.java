/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.evento;

import br.org.abrasf.nfse.CancelarNfseEnvio;
import br.org.abrasf.nfse.CancelarNfseResposta;
import br.org.abrasf.nfse.TcCpfCnpj;
import br.org.abrasf.nfse.TcIdentificacaoNfse;
import br.org.abrasf.nfse.TcInfPedidoCancelamento;
import br.org.abrasf.nfse.TcPedidoCancelamento;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
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

    public Cancelar() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException, NoSuchMethodException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        CancelamentoDAO cancelamentoDAO = new CancelamentoDAO();
        for (Object cancelamento : cancelamentoDAO.getListByNamedQuery("cancelamentosPendentes")) {
            this.tagID = "InfPedidoCancelamento";
            this.objetoModelo = cancelamento;
            super.run();
        }
    }

    @Override
    public String formaXml() {
        String xml = null;
        try {
            Cancelamento cancelamento = (Cancelamento) this.objetoModelo;

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
                       
            xml = MarshallerUtil.marshal(CancelarNfseEnvio.class, cancelarNfseEnvio);        
        
        } catch (IllegalArgumentException | JAXBException ex) {
            Logger.getLogger(Cancelar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xml;
    }

    @Override
    public String enviaXml(String xmlEnvio) {
        return super.getConnectionPort().cancelarNfse(xmlEnvio);
    }

    @Override
    public void retornaXml(String xmlRetorno) {
        try {
            CancelarNfseResposta cneResposta = (CancelarNfseResposta) UnmarshallerUtil.unmarshal(CancelarNfseResposta.class, xmlRetorno);
            Cancelamento cancelamento = (Cancelamento) this.objetoModelo;
            super.setMensagemRetorno(cneResposta.getListaMensagemRetorno().getMensagemRetorno(), "Cancelamento", cancelamento.getId());            
        } catch (JAXBException | IllegalArgumentException ex) {
            Logger.getLogger(Cancelar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
