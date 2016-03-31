/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.util;

/**
 *
 * @author root
 */
public class Conexao {

    public static void certifica() {
        System.clearProperty("javax.net.ssl.keyStore");
        System.clearProperty("javax.net.ssl.keyStorePassword");
        System.clearProperty("javax.net.ssl.trustStore");

        System.setProperty("javax.net.ssl.keyStoreType", "JKS");
        System.setProperty("javax.net.ssl.keyStore", "CERTIFICADO.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "");

        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore", "jssecacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");        
    }

}
