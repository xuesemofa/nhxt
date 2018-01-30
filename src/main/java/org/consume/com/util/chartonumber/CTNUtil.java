package org.consume.com.util.chartonumber;

public class CTNUtil {
    public static int CharToNumber(String s) {
        String p = "一二三四五六七八九十";
        String t = "0";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (p.indexOf(chars[i]) != -1)
                t += p.indexOf(chars[i]) + 1;
            else
                break;
        }
        int i = Integer.parseInt(t);
        return i;
    }
}
