package com.trip.service.impl;

import com.trip.domains.request.OtpRequest;
import com.trip.domains.request.UserVerificationRequest;
import com.trip.domains.response.AppResponse;
import com.trip.domains.response.OtpResponse;
import com.trip.domains.response.UserVerificationResponse;
import com.trip.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    @Override
    public AppResponse<UserVerificationResponse> verify(UserVerificationRequest userVerificationRequest) {
        return null;
    }

    @Override
    public AppResponse<OtpResponse> sendOtp(OtpRequest otpRequest) {
        return null;
    }

    @Override
    public AppResponse<OtpResponse> resendOtp(OtpRequest otpRequest) {
        return null;
    }
}
