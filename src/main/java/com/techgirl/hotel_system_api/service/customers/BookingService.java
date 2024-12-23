package com.techgirl.hotel_system_api.service.customers;

import com.techgirl.hotel_system_api.dto.ReservationDto;
import com.techgirl.hotel_system_api.model.Reservation;
import com.techgirl.hotel_system_api.model.Room;
import com.techgirl.hotel_system_api.model.User;
import com.techgirl.hotel_system_api.model.enums.ReservationStatus;
import com.techgirl.hotel_system_api.model.response.ReservationResponse;
import com.techgirl.hotel_system_api.repository.ReservationRepository;
import com.techgirl.hotel_system_api.repository.RoomRepository;
import com.techgirl.hotel_system_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public static final int RESULT_SEARCH_PER_PAGE = 4;


    public boolean postReservation(ReservationDto reservationDto) {
        Optional<User> user = userRepository.findById(reservationDto.getUserId());
        Optional<Room> room = roomRepository.findById(reservationDto.getRoomId());

        if (user.isPresent() && room.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setUser(user.get());
            reservation.setRoom(room.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            long days = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
            reservation.setPrice(days * room.get().getPrice());

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }


    public ReservationResponse getAllReservationsByUserId(Long userId, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, RESULT_SEARCH_PER_PAGE);
        Page<Reservation> reservations = reservationRepository.findAllByUserId(pageable,userId);

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setReservationDtoList(reservations.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
        reservationResponse.setPageNumber(reservations.getPageable().getPageNumber());
        reservationResponse.setTotalPages(reservations.getTotalPages());

        return reservationResponse;
    }
}
