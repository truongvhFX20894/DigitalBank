package vn.funix.fx20894.java.asm04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String getDivider(){
        return "+------------------------------------------------------+" ;
    }
    public static String getTitle(){
        return "BIEN LAI GIAO DICH";
    }

    public static String getDateTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }

    public static String formatBalance(double amount){
        return String.format("% ,.0f",amount)+"đ";
    }
}
