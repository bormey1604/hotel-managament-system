package com.techgirl.hotel_system_api.controller.customer;

import com.techgirl.hotel_system_api.dto.ReservationDto;
import com.techgirl.hotel_system_api.service.customers.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> postReservation(@RequestBody ReservationDto reservationDto) {
        boolean isSuccess = bookingService.postReservation(reservationDto);
        if (isSuccess) {
            return new ResponseEntity<>(reservationDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(reservationDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bookings/{userId}")
    public ResponseEntity<?> getAllReservationsByUserId(@PathVariable Long userId, @RequestParam Integer pageNumber) {
        try{
            return new ResponseEntity<>(bookingService.getAllReservationsByUserId(userId, pageNumber), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

