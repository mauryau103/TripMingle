package com.trip.controller;

import com.trip.domains.request.UserRegisterRequest;
import com.trip.domains.response.AppResponse;
import com.trip.domains.response.UserRegisterResponse;
import com.trip.service.IUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final IUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<AppResponse<UserRegisterResponse>> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        AppResponse<UserRegisterResponse> userRegisterResponseAppResponse = userDetailsService.registerUser(userRegisterRequest);
        return ResponseEntity.ok(userRegisterResponseAppResponse);
    }
}
