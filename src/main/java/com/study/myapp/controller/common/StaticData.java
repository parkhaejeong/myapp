package com.study.myapp.controller.common;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.Locale;

public class StaticData {
    public static String index(HttpServletRequest request){
        String url = request.getRequestURI();
        String returnUrl = "/screen" + url.substring(0, url.lastIndexOf("/"));
        return returnUrl;
    }

    public static String format(int number) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        return format.format(number);
    }
}
