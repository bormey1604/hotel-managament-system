package com.techgirl.hotel_system_api.service.admin;

import com.techgirl.hotel_system_api.model.Reservation;
import com.techgirl.hotel_system_api.model.Room;
import com.techgirl.hotel_system_api.model.enums.ReservationStatus;
import com.techgirl.hotel_system_api.model.response.ReservationResponse;
import com.techgirl.hotel_system_api.repository.ReservationRepository;
import com.techgirl.hotel_system_api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public static final int RESULT_SEARCH_PER_PAGE = 4;

    public ReservationResponse getAllReservations(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, RESULT_SEARCH_PER_PAGE);
        Page<Reservation> reservations = reservationRepository.findAll(pageable);

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setReservationDtoList(reservations.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
        reservationResponse.setPageNumber(reservations.getPageable().getPageNumber());
        reservationResponse.setTotalPages(reservations.getTotalPages());

        return reservationResponse;
    }

    public boolean updateReservationStatus(Long id, String status){
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isPresent()) {
            Reservation existingReservation = reservationOptional.get();

            if(status.equalsIgnoreCase("Approve")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }else{
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }
            reservationRepository.save(existingReservation);

            Room existingRoom = existingReservation.getRoom();
            existingRoom.setAvailable(false);

            roomRepository.save(existingRoom);

            return true;
        }
        return false;
    }

}
