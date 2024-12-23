package com.techgirl.hotel_system_api.model.response;

import com.techgirl.hotel_system_api.model.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomResponse {
    private List<Room> roomList;
    private Integer totalPages;
    private Integer pageNumber;
}
