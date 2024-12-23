package com.techgirl.hotel_system_api.model.response;

import com.techgirl.hotel_system_api.model.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private Long userId;
    private UserRole userRole;
}
