/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author root
 */
public final class Conexao {

    private String nomeCertificadoJKS;
    private String senhaCertificado;
    
    public Conexao(String nomeCertificadoJKS, String senhaCertificado) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        this.nomeCertificadoJKS = nomeCertificadoJKS;
        this.senhaCertificado = senhaCertificado;
        this.certifica();
    }
    public void certifica() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.clearProperty("javax.net.ssl.keyStore");
        System.clearProperty("javax.net.ssl.keyStorePassword");
        System.clearProperty("javax.net.ssl.trustStore");

        System.setProperty("javax.net.ssl.keyStoreType", "JKS");
        System.setProperty("javax.net.ssl.keyStore", nomeCertificadoJKS);
        System.setProperty("javax.net.ssl.keyStorePassword", senhaCertificado);

        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore", "jssecacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");        
    }

    public String getNomeCertificadoJKS() {
        return nomeCertificadoJKS;
    }

    public void setNomeCertificadoJKS(String nomeCertificadoJKS) {
        this.nomeCertificadoJKS = nomeCertificadoJKS;
    }

    public String getSenhaCertificado() {
        return senhaCertificado;
    }

    public void setSenhaCertificado(String senhaCertificado) {
        this.senhaCertificado = senhaCertificado;
    }
    
    

}
