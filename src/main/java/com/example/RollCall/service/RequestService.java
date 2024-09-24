package com.example.RollCall.service;

import com.example.RollCall.dto.repon.LeaveRequestList;
import com.example.RollCall.dto.repon.RequestRepon;
import com.example.RollCall.dto.request.RequestsRequest;
import com.example.RollCall.entity.Request;
import com.example.RollCall.entity.Status;
import com.example.RollCall.mapper.RequestMapper;
import com.example.RollCall.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    public List<LeaveRequestList> getFindBy (String name){
        return requestRepository.findByName(name);
    }

    public RequestRepon getCreat (RequestsRequest request1) {
        if(requestRepository.findByCheck(request1.getUserId()).isPresent()){
            throw new RuntimeException("Hôm nay bạn đã gửi rồi! ");
        }else {
            Request request = requestMapper.toRequest(request1);
            request.setStatus(Status.PENDING);
            request.setRequestDate(new Date());
            return requestMapper.toRequestRepon(requestRepository.save(request));
        }
    }
    public String delete(Long id){
        if(requestRepository.findById(id).isPresent()){
            requestRepository.deleteById(id);
            return "Xóa thành công";
        }else throw new RuntimeException("Không tồn tại đơn có id "+id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void respondToLaveRequest(Long leaveRequestId ,Status status,String adminCmt){
        Request request = requestRepository.findById(leaveRequestId).orElseThrow(()-> new RuntimeException("Leave request not found"));
        request.setUpdateAt(new Date());
        request.setStatus(status);
        request.setComment(adminCmt);
        requestRepository.save(request);
    }

}
