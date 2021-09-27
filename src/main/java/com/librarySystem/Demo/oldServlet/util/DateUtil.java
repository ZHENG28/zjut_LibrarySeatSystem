package com.librarySystem.Demo.oldServlet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    private DateUtil() {}

    public static String format(Date date, String pattern)
    {
        return new SimpleDateFormat(pattern).format(date);
    }
}
