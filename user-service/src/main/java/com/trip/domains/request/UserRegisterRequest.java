package com.trip.domains.request;

import com.trip.domains.enums.Gender;
import com.trip.domains.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Gender gender;
    private UserStatus userStatus;
    private String countryOfResidence;
    private Boolean isEmailVerified;
}
