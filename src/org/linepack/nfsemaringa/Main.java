/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.linepack.nfsemaringa.DAO.ParametrosDAO;
import org.linepack.nfsemaringa.evento.Cancelar;
import org.linepack.nfsemaringa.evento.Enviar;
import org.linepack.nfsemaringa.model.Parametros;
import org.linepack.nfsemaringa.util.Conexao;
import org.xml.sax.SAXException;

/**
 *
 * @author root
 */
public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, JAXBException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {                
        Parametros parametros = (Parametros) new ParametrosDAO().getByID("Parametros", 1);        
        Conexao conexao =  new Conexao(parametros.getNomeCertificadoJKS(),parametros.getSenhaCertificado());
        
        Cancelar cancelar = new Cancelar(conexao);
        Enviar enviar = new Enviar(conexao);                        
    }
   
}
