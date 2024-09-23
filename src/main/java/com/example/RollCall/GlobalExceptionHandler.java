package com.example.RollCall;

import com.example.RollCall.dto.repon.ResponsiData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ResponsiData<?>> runtimeExceptionHandler(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new ResponsiData<>(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ResponsiData<?>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        ResponsiData<?> responsiData = new ResponsiData<>();
        responsiData.setMessage(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());
        return ResponseEntity.badRequest().body(responsiData);
    }
}
