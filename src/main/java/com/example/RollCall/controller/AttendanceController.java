package com.example.RollCall.controller;

import com.example.RollCall.dto.repon.ResponsiData;
import com.example.RollCall.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/checkin")
    public ResponseEntity<ResponsiData<?>> checkIn(@RequestParam String userId,
                                                   @RequestParam double userLat,
                                                   @RequestParam double userLon) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsiData<>(attendanceService.check_in(userId, userLat, userLon)));
    }


    @GetMapping("")
    public ResponseEntity<ResponsiData<?>> getFindByMonth(@RequestParam int month) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsiData<>("Success","List by month "+month,attendanceService.getFindByMonth(month)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsiData<>(e.getMessage()));
        }
    }

    @GetMapping("/seach")
    public ResponseEntity<ResponsiData<?>> getFindByMonth(@RequestParam String id,@RequestParam int month){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsiData<>("Success","Bạn đã chấm công : "+ attendanceService.totalAttendance(id,month)+" buổi trong tháng "+month,attendanceService.getFindBy(id,month)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsiData<>(e.getMessage()));
        }
    }
}
