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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.linepack.nfsemaringa.DAO.CancelamentoDAO;
import org.linepack.nfsemaringa.model.Cancelamento;
import org.linepack.nfsemaringa.util.Conexao;
import org.linepack.nfsemaringa.util.MarshallerUtil;
import org.linepack.nfsemaringa.util.UnmarshallerUtil;
import org.xml.sax.SAXException;

/**
 *
 * @author root
 */
public class Cancelar extends EventoModelo<Cancelamento> {

    public Cancelar(Conexao conexao) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException, NoSuchMethodException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.tagID = "InfPedidoCancelamento";
        List<Cancelamento> cancelamentos = new CancelamentoDAO().getListByNamedQuery("cancelamentosPendentes");
        for (Cancelamento cancelamento : cancelamentos) {
            this.objetoModelo = cancelamento;
            super.run(conexao);
        }
    }

    @Override
    public String formaXml(Cancelamento cancelamento) {
        String xml = null;
        try {
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
            CancelarNfseResposta resposta = (CancelarNfseResposta) UnmarshallerUtil.unmarshal(CancelarNfseResposta.class, xmlRetorno);
            Cancelamento cancelamento = this.objetoModelo;
            cancelamento.setUsuarioAlteracao("CONECTOR");
            cancelamento.setDataAlteracao(new Date());
            if (resposta.getListaMensagemRetorno() != null) {
                super.setMensagemRetorno(resposta.getListaMensagemRetorno().getMensagemRetorno(), "Cancelamento", cancelamento.getId());
                cancelamento.setIsProblematica(1);
            } else {
                cancelamento.setIsCancelada(1);
            }
            new CancelamentoDAO().update(cancelamento);
        } catch (JAXBException | IllegalArgumentException ex) {
            Logger.getLogger(Cancelar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
