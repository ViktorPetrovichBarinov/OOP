package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class parseFromStringToDate {
    public static Date fromStringToDate(String str) {
        Date parseDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy H:mm");
            parseDate = dateFormat.parse(str);
        } catch (ParseException ignored) {

        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            parseDate = dateFormat.parse(str);
        } catch (ParseException ignored) {
            return null;
        }
        return parseDate;
    }
}
