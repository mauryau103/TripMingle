package com.trip.service;

import com.trip.domains.request.UserRegisterRequest;
import com.trip.domains.response.AppResponse;
import com.trip.domains.response.UserRegisterResponse;

public interface IUserDetailsService {

    AppResponse<UserRegisterResponse> registerUser(UserRegisterRequest userRegisterRequest);

}
