package com.example.RollCall;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println(formattedDateTime);
        String chuoi = "0216522521||Cấn Xuân Tuyên|22042000|Nam|04092022";
        String[] phanTach = chuoi.split("\\|+");

        // In ra các phần tử đã tách
        for (String phanTu : phanTach) {
            System.out.println(phanTu);
        }
        System.out.println("100"+(-5));
    }
}
