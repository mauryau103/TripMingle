package com.trip.controller;

import com.trip.domains.request.OtpRequest;
import com.trip.domains.request.UserVerificationRequest;
import com.trip.domains.response.AppResponse;
import com.trip.domains.response.OtpResponse;
import com.trip.domains.response.UserVerificationResponse;
import com.trip.service.IAuthService;
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
@RequestMapping("/v1/auth")

public class AuthController {

    private final IAuthService authService;

    @PostMapping("/email/send/otp")
    public ResponseEntity<AppResponse<OtpResponse>> sendOtp(@RequestBody OtpRequest otpRequest) {
        AppResponse<OtpResponse> otpResponseAppResponse = authService.sendOtp(otpRequest);
        return ResponseEntity.ok(otpResponseAppResponse);
    }

    @PostMapping("/email/resend/otp")
    public ResponseEntity<AppResponse<OtpResponse>> resendOtp(@RequestBody OtpRequest otpRequest) {
        AppResponse<OtpResponse> otpResponseAppResponse = authService.resendOtp(otpRequest);
        return ResponseEntity.ok(otpResponseAppResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<AppResponse<UserVerificationResponse>> registerUser(@RequestBody UserVerificationRequest userVerificationRequest) {
        AppResponse<UserVerificationResponse> userVerificationResponseAppResponse = authService.verify(userVerificationRequest);
        return ResponseEntity.ok(userVerificationResponseAppResponse);
    }

}