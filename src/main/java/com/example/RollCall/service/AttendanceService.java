package com.example.RollCall.service;

import com.example.RollCall.entity.Attendance;
import com.example.RollCall.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AttendanceService {


    private final AttendanceRepository attendanceRepository;

    public void saveAttendance(String userId,Date checkInTime,long minutesLate) {
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        attendance.setCheckInTime(checkInTime);
        attendance.setMinutesLate(minutesLate);
        attendanceRepository.save(attendance);
    }
    public List<Attendance> getFindByMonth(int month) {
        if(0<month && month<13){
            return attendanceRepository.findbyMonth(month);
        }else throw new RuntimeException("Nhập sai tháng");
    }

    // Hàm tính khoảng cách Haversine
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Bán kính Trái đất theo km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c * 1000;
    }
    public String check_in(String userId,double userLat,double userLon)  {
        // Tọa độ, Vĩ độ điểm cố định (Cty),
        double latFixed = 21.016068;
        double lonFixed = 105.780781;

        Date date = new Date();
        // Tạo thời gian chuẩn 8:00 AM cho ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date expectedCheckInTime = calendar.getTime();
        long minutesLate = 0;

        if (date.after(expectedCheckInTime)) {
            long time = date.getTime() - expectedCheckInTime.getTime();
            minutesLate = time / 60000;
        }

        // Tính toán khoảng cách giữa vị trí người dùng và điểm cố định
        double distance = calculateDistance(userLat, userLon, latFixed, lonFixed);

        if (distance > 50) {
            return "Bạn không ở trong phạm vi 50m từ điểm cố định.";
        }
        return checkIn(userId,minutesLate,date);
    }
    public String checkIn(String userId, long minutesLate,Date date) {
        // Kiểm tra xem người dùng đã check-in trong ngày hôm nay chưa
        Optional<Attendance> todayAttendance = attendanceRepository.findbyDate(userId);
        if (todayAttendance.isPresent()) {
            return "Bạn đã check-in hôm nay rồi!";
        }

        // Nếu chưa check-in, ghi nhận check-in mới
        saveAttendance(userId, date, minutesLate);

        return "Check-in thành công!";
    }

    public List<Attendance> getFindBy(String id, int month) {
        if(attendanceRepository.existsByUserId(id)){
            if(0<month && month<13){
                return attendanceRepository.findByUserIdAndMonth(id, month);
            }else throw new RuntimeException("Nhập sai tháng");
        }else throw new RuntimeException("User không tồn tại! ");
    }
    public int totalAttendance(String id,int month) {
        return attendanceRepository.countByUserIdAndMonth(id,month);
    }
}
