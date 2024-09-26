package com.example.RollCall.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotNull(message = "UserName không thể rỗng.")
    String username;

    @NotNull(message = "Password không thể rỗng.")
    String password;
}
