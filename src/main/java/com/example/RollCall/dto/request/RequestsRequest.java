package com.example.RollCall.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestsRequest {
    private String userId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String comment;
}
