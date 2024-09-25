package com.example.RollCall.controller;

import com.example.RollCall.dto.repon.ResponsiData;
import com.example.RollCall.repository.UserRepository;
import com.example.RollCall.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;
    private final UserRepository userRepository;

    @PostMapping("/creat")
    public ResponseEntity<ResponsiData<?>> total(@RequestParam int month,@RequestParam int year){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                   new ResponsiData<>("200","Created Table Salary",salaryService.getSalaries(month,year))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsiData<>(e.getMessage()));
        }
    }
    @GetMapping("")
    public ResponseEntity<ResponsiData<?>> findBy(@RequestParam String id,@RequestParam int month,@RequestParam int year){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsiData<>("200","Payroll by id ",salaryService.findById(id,month,year))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsiData<>(e.getMessage()));
        }
    }

}
