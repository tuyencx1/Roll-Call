package com.example.RollCall.service;

import com.example.RollCall.dto.repon.UserRespon;
import com.example.RollCall.dto.request.UserRequest;
import com.example.RollCall.entity.Role;
import com.example.RollCall.entity.Users;
import com.example.RollCall.mapper.UserMapper;
import com.example.RollCall.repository.RoleRepository;
import com.example.RollCall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Users> getAllUsers() { return userRepository.findAll(); }
    @PreAuthorize("hasRole('ADMIN')")
    public Users getUserById(String id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }

    public UserRespon getCreatUser(UserRequest request){
        if(userRepository.existsByUserName(request.getUserName())){
            throw new RuntimeException("User already exists");
        }else {
            Users user = userMapper.toUser(request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Role> roles = roleRepository.findAllById(request.getRoles());
            user.setRoles(roles);
            return userMapper.toUserRepon(userRepository.save(user));
        }
    }
    @PostAuthorize("returnObject.userName == authentication.name")
    public UserRespon getUpdateUser(String id,UserRequest request){
        if(userRepository.findById(id).isPresent()){
            Users user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
            userMapper.updateUser(user,request);
            List<Role> roles = roleRepository.findAllById(request.getRoles());
            user.setRoles(roles);
            return userMapper.toUserRepon(userRepository.save(user));
        }else throw new RuntimeException("User already exists");
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Users> searchUsers(String name){
        if(!userRepository.findByNameContaining(name).isEmpty()){
            return userRepository.findByNameContaining(name);
        }else throw new RuntimeException("User not found");

    }
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(String id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return "Xóa thành công ";
        }else throw new RuntimeException("Không tồn tại user có id "+id);
    }
}
