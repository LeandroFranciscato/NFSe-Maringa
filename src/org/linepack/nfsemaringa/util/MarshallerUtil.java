/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.util;

import br.org.abrasf.nfse.CancelarNfseEnvio;
import java.io.CharArrayWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author leandro
 */
public class MarshallerUtil {
    
    public static String marshal(Class contextClass, Object objectToMarshal) throws JAXBException{
         
        JAXBContext context = JAXBContext.newInstance(contextClass);
        Marshaller m = context.createMarshaller();        
        Writer writer = new CharArrayWriter();
        m.marshal(objectToMarshal, writer);
        
        return writer.toString();
        
    }
    
}
