package com.example.RollCall.controller;

import com.example.RollCall.dto.repon.AuthenRepon;
import com.example.RollCall.dto.repon.IntrospectResponse;
import com.example.RollCall.dto.repon.ResponsiData;
import com.example.RollCall.dto.request.AuthenRespuest;
import com.example.RollCall.dto.request.IntrospectRequest;
import com.example.RollCall.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService athenticationService;
    @PostMapping("/log-in")
    public ResponsiData<AuthenRepon> authenticate(@RequestBody AuthenRespuest respuest) {
        var result = athenticationService.authenticated(respuest);
        return ResponsiData.<AuthenRepon>builder().data(result).build();
    }

    @PostMapping("/ktr")
    public ResponsiData<IntrospectResponse> introspecate(@RequestBody IntrospectRequest respuest)
            throws ParseException, JOSEException {
        var result = athenticationService.introspect(respuest);
        return ResponsiData.<IntrospectResponse>builder().data(result).build();
    }
}
