package com.example.RollCall.service;

import com.example.RollCall.entity.Salary;
import com.example.RollCall.entity.Users;
import com.example.RollCall.repository.AttendanceRepository;
import com.example.RollCall.repository.SalaryRepository;
import com.example.RollCall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService {
    private final SalaryRepository salaryRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    @NonFinal
    private static final double LATE_PENALTY_PER_HOUR = 50000;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Salary> getSalaries(int month,int year) {
        List<Users> users = userRepository.findAll();
        List<Salary> salaries = new ArrayList<>();
        for (Users user : users) {
            if (!isSalaryAlreadyCalculated(user.getUserId(), month, year)) {
                Salary salary = calculateSalary(user.getUserId(), month, year);
                salaries.add(salary);
            }else throw new RuntimeException("Bảng lương đã được tạo rồi! ");

        }
        return salaries;
    }

    public boolean isSalaryAlreadyCalculated(String userId, int month, int year) {
        return salaryRepository.existsByUserIdAndMonthAndYear(userId, month, year);
    }

    public Salary calculateSalary(String userId,int month,int year) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        int totalWorkingDay = attendanceRepository.countByUserIdAndMonthAndYear(userId, month, year);

        Double totalLateMinutes = attendanceRepository.sumLateMinutesByUserIdAndMonthAndYear(userId, month, year);
        if (totalLateMinutes == null) {
            totalLateMinutes = (double) 0L;  // Gán giá trị mặc định là 0 nếu không có dữ liệu đi muộn
        }
        totalLateMinutes = Math.round(totalLateMinutes * 100.0) / 100.0;

        var latePenalty = (totalLateMinutes / 60 ) * LATE_PENALTY_PER_HOUR;
        latePenalty = Math.round(latePenalty);
        DecimalFormat df = new DecimalFormat("#,###");
        String arrest =df.format(latePenalty);

        double salaryCoefficient = user.getSalaryRank();
        salaryCoefficient = Math.round(salaryCoefficient * 100.0) / 100.0;

        double baseSalary = 3000000;
        double totalSalary = ((baseSalary * salaryCoefficient * totalWorkingDay)/26) - latePenalty;
        totalSalary = Math.round(totalSalary);

        String formattedTotalSalary = df.format(totalSalary);

        Salary salary = new Salary();
        salary.setUserId(userId);
        salary.setName(user.getName());
        salary.setBasicSalary(baseSalary);
        salary.setSalaryCoefficient(salaryCoefficient);
        salary.setTotalWorkingDay(totalWorkingDay);
        salary.setLatePenalty(arrest + " VNĐ");
        salary.setTotalSalary(formattedTotalSalary + " VNĐ");
        salary.setCreatAt(new Date());

        salaryRepository.save(salary);
        return salary;
    }


    public Salary findById(String userId,int month,int year){
        return calculateSalary(userId, month, year);
    }
}
