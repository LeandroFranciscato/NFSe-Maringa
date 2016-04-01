package org.linepack.nfsemaringa.evento;

import br.org.abrasf.nfse.GerarNfseEnvio;
import br.org.abrasf.nfse.GerarNfseResposta;
import br.org.abrasf.nfse.TcContato;
import br.org.abrasf.nfse.TcCpfCnpj;
import br.org.abrasf.nfse.TcDadosConstrucaoCivil;
import br.org.abrasf.nfse.TcDadosServico;
import br.org.abrasf.nfse.TcDadosTomador;
import br.org.abrasf.nfse.TcDeclaracaoPrestacaoServico;
import br.org.abrasf.nfse.TcEndereco;
import br.org.abrasf.nfse.TcIdentificacaoPrestador;
import br.org.abrasf.nfse.TcIdentificacaoRps;
import br.org.abrasf.nfse.TcIdentificacaoTomador;
import br.org.abrasf.nfse.TcInfDeclaracaoPrestacaoServico;
import br.org.abrasf.nfse.TcInfRps;
import br.org.abrasf.nfse.TcValoresDeclaracaoServico;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.linepack.nfsemaringa.DAO.EnvioDAO;
import org.linepack.nfsemaringa.model.Envio;
import org.linepack.nfsemaringa.util.ConverterUtil;
import org.linepack.nfsemaringa.util.MarshallerUtil;
import org.linepack.nfsemaringa.util.UnmarshallerUtil;
import org.xml.sax.SAXException;

/**
 *
 * @author root
 */
public class Enviar extends EventoModelo {

    public Enviar() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, JAXBException, NoSuchMethodException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        EnvioDAO envioDAO = new EnvioDAO();
        for (Object envio : envioDAO.getListByNamedQuery("enviosPendentes")) {            
            this.tagID = "InfDeclaracaoPrestacaoServico";
            this.objetoModelo = envio;
            super.run();
        }
    }

    @Override
    protected String formaXml() {
        String xml = null;
        try {
            Envio envio = (Envio) this.objetoModelo;
            
            // RPS
            TcIdentificacaoRps identificacaoRps = new TcIdentificacaoRps();
            identificacaoRps.setNumero(BigInteger.valueOf(envio.getNumeroRps()));
            identificacaoRps.setSerie(envio.getSerieRps());
            identificacaoRps.setTipo(Byte.valueOf(envio.getTipoRps()));

            TcInfRps rps = new TcInfRps();
            rps.setIdentificacaoRps(identificacaoRps);
            rps.setDataEmissao(ConverterUtil.dateToXMLGregorianCalendar(envio.getDataEmissaoRps()));
            rps.setStatus(envio.getStatusRps().byteValue());

            // SERVIÇO
            TcValoresDeclaracaoServico valores = new TcValoresDeclaracaoServico();
            valores.setValorServicos(envio.getValorServicos());
            valores.setValorDeducoes(envio.getValorDeducoes());
            valores.setValorPis(envio.getValorPis());
            valores.setValorCofins(envio.getValorCofins());
            valores.setValorInss(envio.getValorInss());
            valores.setValorIr(envio.getValorIr());
            valores.setValorCsll(envio.getValorCsll());
            valores.setOutrasRetencoes(envio.getOutrasRetencoes());
            valores.setValorIss(envio.getValorIss());
            valores.setAliquota(envio.getAliquota());
            valores.setDescontoIncondicionado(envio.getDescontoIncondicionado());
            valores.setDescontoCondicionado(envio.getDescontoCondicionado());

            TcDadosServico servico = new TcDadosServico();
            servico.setValores(valores);
            servico.setIssRetido(envio.getIssRetido().byteValue());
            servico.setResponsavelRetencao(envio.getResponsavelRetencao().byteValue());
            servico.setItemListaServico(envio.getItemListaServico());
            servico.setCodigoCnae(envio.getCodigoCnae());
            servico.setCodigoTributacaoMunicipio(envio.getCodigoTributacaoMunicipio());
            servico.setDiscriminacao(envio.getDiscriminacaoServico());
            servico.setCodigoMunicipio(envio.getCodigoMunicipioServico());
            servico.setCodigoPais(envio.getCodigoPais());
            servico.setExigibilidadeISS(envio.getExigibilidadeIss().byteValue());
            servico.setMunicipioIncidencia(envio.getMunicipioIncidencia());
            servico.setNumeroProcesso(envio.getNumeroProcesso());

            // PRESTADOR
            TcIdentificacaoPrestador prestador = new TcIdentificacaoPrestador();
            TcCpfCnpj cpfCnpjPrestador = new TcCpfCnpj();
            if (envio.getTipoDocumentoPortador() == "CPF") {
                cpfCnpjPrestador.setCpf(envio.getNumeroCnpjCpfPortador());
            } else {
                cpfCnpjPrestador.setCnpj(envio.getNumeroCnpjCpfPortador());
            }
            prestador.setCpfCnpj(cpfCnpjPrestador);
            prestador.setInscricaoMunicipal(envio.getInscricaoMunicipalPortador());

            // TOMADOR
            TcIdentificacaoTomador idTomador = new TcIdentificacaoTomador();
            TcCpfCnpj cpfCnpjTomador = new TcCpfCnpj();
            if (envio.getTipoDocumentoTomador() == "CPF") {
                cpfCnpjTomador.setCpf(envio.getNumeroCnpjCpfTomador());
            } else {
                cpfCnpjTomador.setCnpj(envio.getNumeroCnpjCpfTomador());
            }
            idTomador.setCpfCnpj(cpfCnpjTomador);
            idTomador.setInscricaoMunicipal(envio.getInscricaoMunicipalTomador());

            TcDadosTomador tomador = new TcDadosTomador();
            tomador.setIdentificacaoTomador(idTomador);
            tomador.setRazaoSocial(envio.getRazaoSocialTomador());

            TcEndereco endereco = new TcEndereco();
            endereco.setEndereco(envio.getEnderecoTomador());
            endereco.setNumero(envio.getNumeroEnderecoTomador());
            endereco.setComplemento(envio.getComplementoEnderecoTomador());
            endereco.setBairro(envio.getBairroTomador());
            endereco.setCodigoMunicipio(envio.getCodigoMunicipioTomador());
            endereco.setUf(envio.getUfTomador());
            endereco.setCodigoPais(envio.getCodigoPais());
            endereco.setCep(envio.getCepTomador());

            tomador.setEndereco(endereco);

            TcContato contato = new TcContato();
            contato.setTelefone(envio.getTelefoneTomador());
            contato.setEmail(envio.getEmailTomador());

            tomador.setContato(contato);

            // GERAL
            TcInfDeclaracaoPrestacaoServico inf = new TcInfDeclaracaoPrestacaoServico();
            inf.setId(envio.getId().toString());
            inf.setRps(rps);
            inf.setCompetencia(ConverterUtil.dateToXMLGregorianCalendar(envio.getDataCompetenciaRps()));
            inf.setServico(servico);
            inf.setPrestador(prestador);
            inf.setTomador(tomador);

            // CONSTRUÇÃO CIVIL            
            if (envio.getCodigoObra() != null) {
                TcDadosConstrucaoCivil civil = new TcDadosConstrucaoCivil();
                civil.setCodigoObra(envio.getCodigoObra());
                civil.setArt(envio.getArtObra());
                inf.setConstrucaoCivil(civil);
            }

            if (envio.getRegimeEspecialTributacao() != null) {
                inf.setRegimeEspecialTributacao(envio.getRegimeEspecialTributacao().byteValue());
            }
            inf.setOptanteSimplesNacional(envio.getOptanteSimplesNacional().byteValue());
            inf.setIncentivoFiscal(envio.getIncentivoFiscal().byteValue());

            TcDeclaracaoPrestacaoServico declaracao = new TcDeclaracaoPrestacaoServico();
            declaracao.setInfDeclaracaoPrestacaoServico(inf);

            GerarNfseEnvio nfseEnvio = new GerarNfseEnvio();
            nfseEnvio.setRps(declaracao);

            xml = MarshallerUtil.marshal(GerarNfseEnvio.class, nfseEnvio);

        } catch (DatatypeConfigurationException | JAXBException | IllegalArgumentException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xml;
    }

    @Override
    protected String enviaXml(String xmlEnvio) {
        return super.getConnectionPort().gerarNfse(xmlEnvio);
    }

    @Override
    protected void retornaXml(String xmlRetorno) {
        try {
            GerarNfseResposta resposta = (GerarNfseResposta) UnmarshallerUtil.unmarshal(GerarNfseResposta.class, xmlRetorno);
            Envio envio = (Envio) this.objetoModelo;
            if (resposta.getListaMensagemRetorno() != null) {
                super.setMensagemRetorno(resposta.getListaMensagemRetorno().getMensagemRetorno(), "Envio", envio.getId());
            }else{
                envio.setIsEnviada(1);
                new EnvioDAO().update(envio);
            }
        } catch (JAXBException | IllegalArgumentException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
