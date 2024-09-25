package com.example.RollCall.controller;

import com.example.RollCall.dto.repon.ResponsiData;
import com.example.RollCall.dto.request.UserRequest;
import com.example.RollCall.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<ResponsiData<?>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponsiData<>("200","Get All User",userService.getAllUsers()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponsiData<?>> getUserById(@PathVariable String id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponsiData<>("200","Get User",userService.getUserById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsiData<>(e.getMessage()));
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponsiData<?>> getsearchUsers(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsiData<>("200","Search User By Name ",userService.searchUsers(name))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsiData<>(e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponsiData<?>> addUser(@RequestBody @Valid UserRequest user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponsiData<>("201","Creat User Susecc",userService.getCreatUser(user))
            );
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsiData<>(e.getMessage()));
        }
    }
    @PostMapping("update/{id}")
    public ResponseEntity<ResponsiData<?>> updateUser(@PathVariable String id,@RequestBody UserRequest user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsiData<>("200","Update Susecc",userService.getUpdateUser(id,user))
            );
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsiData<>(e.getMessage()));
        }
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        try {
            userService.deleteUserById(id);
            return "Xóa thành công! id "+id;
        }catch (RuntimeException e) {
            return  e.getMessage();
        }
    }
}
