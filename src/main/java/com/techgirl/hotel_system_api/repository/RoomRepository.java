package com.techgirl.hotel_system_api.repository;

import com.techgirl.hotel_system_api.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findAllByAvailable(boolean available,Pageable pageable);
}
