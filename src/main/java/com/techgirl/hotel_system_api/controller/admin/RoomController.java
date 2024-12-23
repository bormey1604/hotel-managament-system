package com.techgirl.hotel_system_api.controller.admin;

import com.techgirl.hotel_system_api.model.Room;
import com.techgirl.hotel_system_api.service.admin.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/room")
    public ResponseEntity<?> postRoom(@RequestBody Room room) {
        boolean isSuccess = roomService.postRoom(room);

        if(isSuccess) {
            return new ResponseEntity<>(room,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/room")
    public ResponseEntity<?> getAllRooms(@RequestParam int pageNumber) {
        return new ResponseEntity<>(roomService.getAllRooms(pageNumber),HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(roomService.getRoomById(id),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        boolean isSuccess = roomService.updateRoom(id, room);

        if(isSuccess) {
            return new ResponseEntity<>(room,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<?> deleteRoomById(@PathVariable Long id) {
        try{
            roomService.deleteRoom(id);
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
