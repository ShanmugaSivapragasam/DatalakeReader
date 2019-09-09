package com.shan.datalakereader.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final String HYPHENATED_MILL_SECONDS = "dd-MM-yyyy-HH-mm-ss-SSS";
    private static final String HYPHENATED_DATE = "dd-MM-yyyy-HH-mm-ss-SSS";


    public static String getCurrentLocaTimeInHypenMilliSeconds(){

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(HYPHENATED_MILL_SECONDS);
        return  currentTime.format(formatter);
    }

    public  static String getCurrentLocalDateHyphenated(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(HYPHENATED_DATE);
        return LocalDateTime.now().format(formatter);
    }
}
