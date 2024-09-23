package com.example.RollCall;
import java.text.DecimalFormat;

public class test {
    public static void main(String[] args) {
        double totalSalary = 3000000;

// Sử dụng DecimalFormat với mẫu định dạng
        DecimalFormat df = new DecimalFormat("#.###");
        String formattedTotalSalary = df.format(totalSalary);

        System.out.println("Total Salary: " + formattedTotalSalary);
    }
}
