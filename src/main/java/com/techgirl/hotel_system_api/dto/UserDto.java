package com.techgirl.hotel_system_api.dto;

import com.techgirl.hotel_system_api.model.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
