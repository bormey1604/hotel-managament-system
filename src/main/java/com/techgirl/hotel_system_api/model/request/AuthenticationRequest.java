package com.techgirl.hotel_system_api.model.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
