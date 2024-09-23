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
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date checkInTime;
    private long minutesLate;
    private String notes;

    @Column(name = "userId")
    private String userId;
}
