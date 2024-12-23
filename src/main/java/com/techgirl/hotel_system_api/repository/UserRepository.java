package com.techgirl.hotel_system_api.repository;

import com.techgirl.hotel_system_api.model.User;
import com.techgirl.hotel_system_api.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}
