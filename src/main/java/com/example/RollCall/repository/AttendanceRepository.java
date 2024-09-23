package com.example.RollCall.repository;

import com.example.RollCall.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,String > {
    @Query("SELECT p FROM Attendance p WHERE MONTH(p.checkInTime) = :month")
    List<Attendance> findbyMonth(@Param("month") int month);

    @Query("select a from Attendance a where a.userId = :userId and DATE(a.checkInTime) = current_date ")
    Optional<Attendance> findbyDate(@Param("userId") String userId);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.userId = :userId AND FUNCTION('MONTH', a.checkInTime) = :month " +
            "AND FUNCTION('YEAR', a.checkInTime) = :year")
    int countByUserIdAndMonthAndYear(@Param("userId") String userId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(a.minutesLate) FROM Attendance a WHERE a.userId = :userId AND FUNCTION('MONTH', a.checkInTime) = :month " +
            "AND FUNCTION('YEAR', a.checkInTime) = :year")
    Double sumLateMinutesByUserIdAndMonthAndYear(@Param("userId") String userId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT a FROM Attendance a WHERE a.userId =:userId AND MONTH(a.checkInTime) = :month ")
    List<Attendance> findByUserIdAndMonth(@Param("userId") String userId, @Param("month") int month);

    boolean existsByUserId(String id);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.userId = :userId AND FUNCTION('MONTH', a.checkInTime) = :month ")
    int countByUserIdAndMonth(@Param("userId") String userId, @Param("month") int month);

}
