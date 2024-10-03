package com.trip.domains.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVerificationRequest {
    private UserVerificationRequest userVerificationRequest;
    private String primaryDetail;
    private String otp;
}
