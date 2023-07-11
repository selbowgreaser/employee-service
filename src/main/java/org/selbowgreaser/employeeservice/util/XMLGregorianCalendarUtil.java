package org.selbowgreaser.employeeservice.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class XMLGregorianCalendarUtil {

    public static XMLGregorianCalendar now() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DatatypeFactory datatypeFactory;
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }

        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar();

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(timestamp);
        xmlGregorianCalendar.setTime(gregorianCalendar.get(GregorianCalendar.HOUR_OF_DAY),
                gregorianCalendar.get(GregorianCalendar.MINUTE),
                gregorianCalendar.get(GregorianCalendar.SECOND),
                gregorianCalendar.get(GregorianCalendar.MILLISECOND));

        gregorianCalendar.setTime(timestamp);
        xmlGregorianCalendar.setYear(gregorianCalendar.get(GregorianCalendar.YEAR));
        xmlGregorianCalendar.setMonth(gregorianCalendar.get(GregorianCalendar.MONTH) + 1);
        xmlGregorianCalendar.setDay(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));

        xmlGregorianCalendar.setTimezone(TimeZone.getDefault().getRawOffset() / (1000 * 60));

        return xmlGregorianCalendar;
    }

    public static LocalDate mapXMLGregorianCalendarToLocalDate(XMLGregorianCalendar xmlGregorianCalendar) {
        return LocalDate.of(xmlGregorianCalendar.getYear(), xmlGregorianCalendar.getMonth(), xmlGregorianCalendar.getDay());
    }

    public static XMLGregorianCalendar mapLocalDateToXMLGregorianCalendar(LocalDate localDate) {
        XMLGregorianCalendar xmlGregorianCalendar;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return xmlGregorianCalendar;
    }
}
