package com.magic.sso.util;

public class StringUtil {
    public static boolean isBlank(String string) {
        return string == null || "".equals(string);
    }

    public static boolean isAllBlank(String... strings) {
        System.out.println("sdfsdfsdf");
        int a=0;
        for (String s : strings) {
            if (isBlank(s)) {
                return true;
            }
        }
        return false;
    }
}