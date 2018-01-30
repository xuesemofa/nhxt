package org.consume.com.util.string;

public class StringToNumber {
    public static String stn(String s) {
//        String a = "love23next234csdn3423javaeye";
//        String regEx = "[^0-9]";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(args);
        s = s.replaceAll("高温", "");
        s = s.replaceAll("低温", "");
        s = s.replaceAll("级", "");
        s = s.replaceAll("=", "");
        s = s.replaceAll("℃", "");
        s = s.replaceAll("<", "");
        s = s.replaceAll(">", "");
        s = s.replaceAll("<=", "");
        s = s.replaceAll(">=", "");
        s = s.trim();
        return s;
    }

    public static String stn2(String s) {
        s = s.replaceAll("级", "");
        s = s.replaceAll("=", "");
        s = s.replaceAll("<", "");
        s = s.replaceAll(">", "");
        s = s.replaceAll("<=", "");
        s = s.replaceAll(">=", "");
        s = s.replaceAll("-", ".");
        s = s.trim();
        return s;
    }

//    public static void main(String[] args) {
//        String s = "高温 -7.0℃";
//        s = s.replaceAll("高温", "");
//        s = s.replaceAll("低温", "");
//        s = s.replaceAll("℃", "");
//        s = s.trim();
//        System.out.println(s);
//    }

}
