package org.selbowgreaser.employeeservice.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class XMLGregorianCalendarUtil {


    /**
     * Get the current date and time as an XMLGregorianCalendar object.
     *
     * @return The current date and time as an XMLGregorianCalendar.
     */
    public static XMLGregorianCalendar now() { //todo тут какая то магия, не понятно что делает. done
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DatatypeFactory datatypeFactory = getDatatypeFactory();

        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar();

        GregorianCalendar gregorianCalendar = getGregorianCalendar(timestamp, xmlGregorianCalendar);

        setTimeGregorianCalendar(timestamp, xmlGregorianCalendar, gregorianCalendar);

        return xmlGregorianCalendar;
    }

    /**
     * Set the time components of the XMLGregorianCalendar based on the given Timestamp.
     *
     * @param timestamp                The Timestamp object representing the current time.
     * @param xmlGregorianCalendar    The XMLGregorianCalendar object to be modified.
     * @param gregorianCalendar        The GregorianCalendar object representing the current time.
     */
    private static void setTimeGregorianCalendar(Timestamp timestamp, XMLGregorianCalendar xmlGregorianCalendar, GregorianCalendar gregorianCalendar) {
        gregorianCalendar.setTime(timestamp);
        xmlGregorianCalendar.setYear(gregorianCalendar.get(GregorianCalendar.YEAR));
        xmlGregorianCalendar.setMonth(gregorianCalendar.get(GregorianCalendar.MONTH) + 1);
        xmlGregorianCalendar.setDay(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));

        xmlGregorianCalendar.setTimezone(TimeZone.getDefault().getRawOffset() / (1000 * 60));
    }

    /**
     * Get a new GregorianCalendar object based on the given Timestamp.
     *
     * @param timestamp                The Timestamp object representing the current time.
     * @param xmlGregorianCalendar    The XMLGregorianCalendar object to be modified.
     * @return The new GregorianCalendar object.
     */
    private static GregorianCalendar getGregorianCalendar(Timestamp timestamp, XMLGregorianCalendar xmlGregorianCalendar) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(timestamp);
        xmlGregorianCalendar.setTime(gregorianCalendar.get(GregorianCalendar.HOUR_OF_DAY),
                gregorianCalendar.get(GregorianCalendar.MINUTE),
                gregorianCalendar.get(GregorianCalendar.SECOND),
                gregorianCalendar.get(GregorianCalendar.MILLISECOND));
        return gregorianCalendar;
    }

    /**
     * Get the DatatypeFactory instance.
     *
     * @return The DatatypeFactory instance.
     * @throws RuntimeException If an error occurs while creating the DatatypeFactory instance.
     */
    private static DatatypeFactory getDatatypeFactory() {
        DatatypeFactory datatypeFactory;
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return datatypeFactory;
    }

    /**
     * Convert an XMLGregorianCalendar to a LocalDate object.
     *
     * @param xmlGregorianCalendar The XMLGregorianCalendar object to be converted.
     * @return The LocalDate object representing the same date as the XMLGregorianCalendar.
     */
    public static LocalDate mapXMLGregorianCalendarToLocalDate(XMLGregorianCalendar xmlGregorianCalendar) {
        return LocalDate.of(xmlGregorianCalendar.getYear(), xmlGregorianCalendar.getMonth(), xmlGregorianCalendar.getDay());
    }

    /**
     * Convert a LocalDate object to an XMLGregorianCalendar object.
     *
     * @param localDate The LocalDate object to be converted.
     * @return The XMLGregorianCalendar object representing the same date as the LocalDate.
     * @throws RuntimeException If an error occurs while creating the XMLGregorianCalendar object.
     */
    public static XMLGregorianCalendar mapLocalDateToXMLGregorianCalendar(LocalDate localDate) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
