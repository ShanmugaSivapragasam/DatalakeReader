package com.shan.datalakereader.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static final String HYPHENATED_MILL_SECONDS = "dd-MM-yyyy-HH-mm-ss-SSS";

    public static String getCurrentLocaTimeInHypenMilliSeconds(){

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(HYPHENATED_MILL_SECONDS);
        return  currentTime.format(formatter);
    }
}
