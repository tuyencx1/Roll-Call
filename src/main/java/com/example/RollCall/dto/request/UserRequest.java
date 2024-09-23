package com.example.RollCall.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Size(min = 3, message = "Tên đăng nhập ít nhất có 3 ký tự!")
    private String userName;

    @Size(min = 8, message = "Mật khẩu có ít nhất 8 ký tự")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Mật khẩu phải chứa ít nhất 1 chữ cái viết hoa,1 ký tự đặc biệt và 1 số")
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
    private List<String> roles;
}