package com.example.RollCall.dto.repon;

import com.example.RollCall.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestList {
    private Long id;
    private String userId;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date requestDate;
    private String reason;
    private Status status;
    private String comment;
}
