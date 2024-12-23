package com.techgirl.hotel_system_api.dto;

import com.techgirl.hotel_system_api.model.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double price;
    private ReservationStatus reservationStatus;
    private Long roomId;
    private String roomType;
    private String roomName;
    private Long userId;
    private String userName;
}
