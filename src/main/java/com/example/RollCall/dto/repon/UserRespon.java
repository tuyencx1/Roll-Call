package com.example.RollCall.dto.repon;

import com.example.RollCall.entity.Role;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRespon {

    private String userName;
    private String password;
    private String name;
    private boolean gender;
    private String address;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    private int phone;
    private int idCard;
    private float salaryRank;
    private List<Role> roles;
}
