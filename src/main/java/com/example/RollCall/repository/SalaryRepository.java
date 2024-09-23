package com.example.RollCall.repository;

import com.example.RollCall.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    @Query("SELECT COUNT(s) > 0 FROM Salary s WHERE s.userId = :userId AND MONTH(s.creatAt) = :month AND YEAR(s.creatAt) = :year")
    boolean existsByUserIdAndMonthAndYear(@Param("userId") String userId, @Param("month") int month, @Param("year") int year);
}
