/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.nfsemaringa.util;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.sql.rowset.serial.SerialClob;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author root
 */
public class ConverterUtil {

    public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        return xgc;
    }
    
    public static Clob stringToClob(String string) throws SQLException{        
        Clob clob = new SerialClob(string.toCharArray());        
        return clob;                       
    }

}
