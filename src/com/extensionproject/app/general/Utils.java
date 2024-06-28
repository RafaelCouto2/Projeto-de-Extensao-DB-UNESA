package com.extensionproject.app.general;

import java.awt.*;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    private static Calendar calendar = Calendar.getInstance();
    public static Calendar getTime(){
        return calendar;
    }

    public static Font jetmono = new Font("JetBrains Mono", Font.BOLD, 13);
}
