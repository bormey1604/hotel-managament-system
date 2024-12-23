package com.techgirl.hotel_system_api.controller.admin;

import com.techgirl.hotel_system_api.service.admin.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<?> getAllReservations(@RequestParam int pageNumber) {
        try{
            return new ResponseEntity<>(reservationService.getAllReservations(pageNumber), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<?> updateReservationStatus(@PathVariable Long id, @RequestParam String status) {
        boolean isSuccess = reservationService.updateReservationStatus(id, status);
        if(isSuccess){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
