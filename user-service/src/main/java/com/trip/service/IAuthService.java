package com.trip.service;

import com.trip.domains.request.OtpRequest;
import com.trip.domains.request.UserVerificationRequest;
import com.trip.domains.response.AppResponse;
import com.trip.domains.response.OtpResponse;
import com.trip.domains.response.UserVerificationResponse;

public interface IAuthService {
    AppResponse<UserVerificationResponse> verify(UserVerificationRequest userVerificationRequest);

    AppResponse<OtpResponse> sendOtp(OtpRequest otpRequest);

    AppResponse<OtpResponse> resendOtp(OtpRequest otpRequest);


}
