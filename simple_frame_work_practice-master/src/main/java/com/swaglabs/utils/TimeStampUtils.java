package com.swaglabs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampUtils {
    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd-HH-mm";


    public static String getTimestamp() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(TIMESTAMP_PATTERN);
        return formatter.format(date);
    }

}
