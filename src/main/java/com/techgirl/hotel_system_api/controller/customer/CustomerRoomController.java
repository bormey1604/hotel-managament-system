package com.techgirl.hotel_system_api.controller.customer;


import com.techgirl.hotel_system_api.service.customers.CustomerRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerRoomController {

    private final CustomerRoomService roomService;

    @GetMapping("/room")
    public ResponseEntity<?> getAvailableRooms(@RequestParam int pageNumber) {
        return ResponseEntity.ok(roomService.getAvailableRooms(pageNumber));
    }
}
