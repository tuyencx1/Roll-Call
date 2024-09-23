package com.example.RollCall.controller;

import com.example.RollCall.dto.repon.ResponsiData;
import com.example.RollCall.dto.request.RequestsRequest;
import com.example.RollCall.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/letter")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping("")
    public ResponseEntity<ResponsiData<?>> getFindByName(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsiData<>("200","Get find by username",requestService.getFindBy(name))
            );
        }catch (RuntimeException e){
            throw new RuntimeException("Error !");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponsiData<?>> add(@RequestBody RequestsRequest request){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponsiData<>("200","Creat success",requestService.getCreat(request))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponsiData<>(e.getMessage()));
        }
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        try {
            requestService.delete(id);
            return "Xóa thành công!";
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
