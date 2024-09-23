package com.example.RollCall.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salary {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Column(name = "userId")
    private String userId;
    private String name;
    private double basicSalary;
    private double salaryCoefficient;//hệ số
    private double latePenalty; //tiền phạt
    private int totalWorkingDay;//số ngày đi làm
    private int unpaidLeaveDay;//số ngày nghỉ
    private String totalSalary;
    private Date creatAt;
}
