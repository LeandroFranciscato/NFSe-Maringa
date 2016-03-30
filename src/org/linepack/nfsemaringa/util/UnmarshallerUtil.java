/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.util;

import br.org.abrasf.nfse.ConsultarNfseFaixaResposta;
import java.io.CharArrayReader;
import java.io.Reader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author leandro
 */
public class UnmarshallerUtil {

    public static Object unmarshal(Class contextClass, String xmlToUnmarshal) throws JAXBException {
        JAXBContext contextRetorno = JAXBContext.newInstance(contextClass);
        Unmarshaller unmarshaller = contextRetorno.createUnmarshaller();
        Reader reader = new CharArrayReader(xmlToUnmarshal.toCharArray());
        Object objetoDestino = (Object) unmarshaller.unmarshal(reader);
        
        return objetoDestino;
    }

}
