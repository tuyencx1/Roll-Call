package com.example.RollCall.controller;

import com.example.RollCall.dto.repon.AuthenRepon;
import com.example.RollCall.dto.repon.IntrospectResponse;
import com.example.RollCall.dto.repon.ResponsiData;
import com.example.RollCall.dto.request.AuthenticationRequest;
import com.example.RollCall.dto.request.IntrospectRequest;
import com.example.RollCall.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/log-in")
    public ResponsiData<AuthenRepon> authenticate(@Validated @RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticated(request);
        return ResponsiData.<AuthenRepon>builder().data(result).build();
    }

    @GetMapping("/ktr")
    public ResponsiData<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ResponsiData.<IntrospectResponse>builder().data(result).build();
    }
}
