package com.example.BookShop.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Converter {

    LocalDate dateFormat;

    public LocalDate getDateType() {
        return dateFormat;
    }

    public Converter(String givenString) throws ParseException {
        DateFormat currentFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat properFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDateFormat = currentFormat.parse(givenString);
        String formattedString = properFormat.format(utilDateFormat);
        dateFormat = LocalDate.parse(formattedString);
    }
}
