package com.example.prototipo1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHandler {

    private Date date;
    private Calendar calendar;
    private DateFormat dateFormat;
    private final String dbPattern = "yyMMddHHmmss";
    private final String acPattern = "HH:mm:ss";


    public String getTimeStampDB() {
        String timeStamp;
        date = new Date();
        calendar = new GregorianCalendar();
        calendar.setTime(date);
        dateFormat = new SimpleDateFormat(dbPattern);
        timeStamp = dateFormat.format(calendar.getTime());

        return timeStamp;
    }

    public String formatDBDate(String dbDate) {
        String formatted = "";
        try {
            dateFormat = new SimpleDateFormat(dbPattern);
            date = dateFormat.parse(dbDate);
            calendar = new GregorianCalendar();
            calendar.setTime(date);
            dateFormat = new SimpleDateFormat(acPattern);
            formatted = dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            System.out.println("Error al formatear el TimeStamp de la DB: " + e);
        }

        return formatted;
    }

}
