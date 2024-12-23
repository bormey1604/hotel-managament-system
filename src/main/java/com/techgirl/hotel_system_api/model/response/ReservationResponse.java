package com.techgirl.hotel_system_api.model.response;

import com.techgirl.hotel_system_api.dto.ReservationDto;
import com.techgirl.hotel_system_api.model.Room;
import lombok.Data;

import java.util.List;

@Data
public class ReservationResponse {
    private List<ReservationDto> reservationDtoList;
    private Integer totalPages;
    private Integer pageNumber;
}
