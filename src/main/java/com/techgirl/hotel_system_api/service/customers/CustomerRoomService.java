package com.techgirl.hotel_system_api.service.customers;

import com.techgirl.hotel_system_api.model.Room;
import com.techgirl.hotel_system_api.model.response.RoomResponse;
import com.techgirl.hotel_system_api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRoomService {
    private final RoomRepository roomRepository;

    public RoomResponse getAvailableRooms(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> rooms = roomRepository.findAllByAvailable(true,pageable);

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setPageNumber(rooms.getPageable().getPageNumber());
        roomResponse.setTotalPages(rooms.getTotalPages());
        roomResponse.setRoomList(rooms.stream().toList());

        return roomResponse;
    }
}
