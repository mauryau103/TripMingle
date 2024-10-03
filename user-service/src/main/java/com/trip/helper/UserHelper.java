package com.trip.helper;

import com.trip.domains.response.UserRegisterResponse;
import com.trip.models.UserDetails;

public final class UserHelper {
    private UserHelper() {
    }

    public static UserRegisterResponse buildUserRegisterResponse(UserDetails userDetails) {
        return UserRegisterResponse.builder().fullName(userDetails.getFullName()).build();
    }

}
