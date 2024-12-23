package com.techgirl.hotel_system_api.model.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String name;
}
