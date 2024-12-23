package com.techgirl.hotel_system_api.model;

import com.techgirl.hotel_system_api.dto.ReservationDto;
import com.techgirl.hotel_system_api.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double price;
    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public ReservationDto getReservationDto(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(id);
        reservationDto.setCheckInDate(checkInDate);
        reservationDto.setCheckOutDate(checkOutDate);
        reservationDto.setPrice(price);
        reservationDto.setReservationStatus(reservationStatus);

        reservationDto.setRoomId(room.getId());
        reservationDto.setRoomType(room.getType());
        reservationDto.setRoomName(room.getName());

        reservationDto.setUserId(user.getId());
        reservationDto.setUserName(user.getName());

        return reservationDto;

    }
}
