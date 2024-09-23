package com.example.RollCall.repository;

import com.example.RollCall.dto.repon.LeaveRequestList;
import com.example.RollCall.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("select a from Request a where a.userId =:userId and DATE(a.requestDate) = current_date ")
    Optional<Request> findByCheck(@Param("userId") String userId);

    @Query("select new com.example.RollCall.dto.repon.LeaveRequestList(a.id,a.userId,b.name,a.startDate,a.endDate,a.requestDate,a.reason,a.status,a.comment) " +
            "from Request a join Users b on a.userId=b.userId where b.name like %:name%")
    List<LeaveRequestList> findByName(@Param("name") String name);

}
