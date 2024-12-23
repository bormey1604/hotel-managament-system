package com.techgirl.hotel_system_api.service.admin;

import com.techgirl.hotel_system_api.model.Room;
import com.techgirl.hotel_system_api.model.response.RoomResponse;
import com.techgirl.hotel_system_api.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public boolean postRoom(Room room) {
        try{
            roomRepository.save(room);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public RoomResponse getAllRooms(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> rooms = roomRepository.findAll(pageable);

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setPageNumber(rooms.getPageable().getPageNumber());
        roomResponse.setTotalPages(rooms.getTotalPages());
        roomResponse.setRoomList(rooms.stream().toList());

        return roomResponse;
    }

    public Room getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            return room.get();
        }else{
            throw new EntityNotFoundException("Room not present");
        }
    }

    public boolean updateRoom(Long id, Room room) {
        Optional<Room> optionalRoom = roomRepository.findById(id);

        if(optionalRoom.isPresent()){
            Room existingRoom = optionalRoom.get();

            existingRoom.setName(room.getName());
            existingRoom.setType(room.getType());
            existingRoom.setPrice(room.getPrice());

            roomRepository.save(existingRoom);
            return true;
        }
        return false;
    }

    public void deleteRoom(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            roomRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Room not present");
        }
    }
}
