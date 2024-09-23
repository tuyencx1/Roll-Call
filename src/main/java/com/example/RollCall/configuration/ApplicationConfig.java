package com.example.RollCall.configuration;

import com.example.RollCall.entity.Role;
import com.example.RollCall.entity.Users;
import com.example.RollCall.repository.RoleRepository;
import com.example.RollCall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner applicationRunner(UserRepository userReponsitory, RoleRepository roleReponsitory ) {
       return args -> {
           if(userReponsitory.findByUserName("admin").isEmpty()){
               List<Role> roles = roleReponsitory.findByName("ADMIN");
               Users users = Users.builder()
                       .userName("admin")
                       .password(passwordEncoder.encode("admin"))
                       .roles(roles)
                       .build();
               userReponsitory.save(users);
               log.warn("User admin has been created with account and password: admin");
           }
       };
    }
}
