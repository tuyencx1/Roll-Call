package com.example.RollCall.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String userName;
    private String password;
    private String name;
    private boolean gender;
    private String address;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private int phone;
    private int idCard;
    private float salaryRank;

    @ManyToMany
    private List<Role> roles;
}
