package com.techgirl.hotel_system_api.service.auth;

import com.techgirl.hotel_system_api.dto.UserDto;
import com.techgirl.hotel_system_api.model.User;
import com.techgirl.hotel_system_api.model.enums.UserRole;
import com.techgirl.hotel_system_api.model.request.SignupRequest;
import com.techgirl.hotel_system_api.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> admin = userRepository.findByUserRole(UserRole.ADMIN);
        if(admin.isPresent()){
            System.out.println("Admin account already exists");
        }else{
            User user = new User();
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setEmail("admin@techgirl.com");
            user.setName("Admin");
            userRepository.save(user);
        }
    }

    public UserDto createUser(SignupRequest request){
        if(userRepository.findFirstByEmail(request.getEmail()).isPresent()){
            throw new EntityExistsException("User already exists with email: " + request.getEmail());
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setName(request.getName());
        user.setUserRole(UserRole.CUSTOMER);

        User savedUser = userRepository.save(user);

        return savedUser.getUserDto();

    }

}
