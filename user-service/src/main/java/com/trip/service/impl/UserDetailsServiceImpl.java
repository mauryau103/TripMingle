package com.trip.service.impl;

import com.trip.domains.request.UserRegisterRequest;
import com.trip.domains.response.AppResponse;
import com.trip.domains.response.UserRegisterResponse;
import com.trip.exception.UnprocessableException;
import com.trip.helper.UserHelper;
import com.trip.models.UserDetails;
import com.trip.repository.UserDetailsRepository;
import com.trip.service.IUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements IUserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public AppResponse<UserRegisterResponse> registerUser(UserRegisterRequest userRegisterRequest) {
        if (Boolean.FALSE.equals(userRegisterRequest.getIsEmailVerified())) {
            throw new UnprocessableException("Please Verify the Email Address");
        }
        //TODO:- Verify Email from backend means kya backend me true hai kya kii usne verify kiya hai ya nahi kiya hai i mean 2 way verification
        UserDetails userDetails = UserDetails.builder()
                .fullName(userRegisterRequest.getFullName())
                .email(userRegisterRequest.getEmail())
                .phoneNumber(userRegisterRequest.getPhoneNumber())
                .dateOfBirth(userRegisterRequest.getDateOfBirth())
                .gender(userRegisterRequest.getGender())
                .userStatus(userRegisterRequest.getUserStatus())
                .countryOfResidence(userRegisterRequest.getCountryOfResidence())
                .build();
        userDetailsRepository.save(userDetails);

        return new AppResponse<>(HttpStatus.OK.value(),
                "User saved successfully", UserHelper.buildUserRegisterResponse(userDetails), null);
    }
}
