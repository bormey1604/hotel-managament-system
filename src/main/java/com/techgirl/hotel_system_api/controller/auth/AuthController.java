package com.techgirl.hotel_system_api.controller.auth;

import com.techgirl.hotel_system_api.model.User;
import com.techgirl.hotel_system_api.model.request.AuthenticationRequest;
import com.techgirl.hotel_system_api.model.request.SignupRequest;
import com.techgirl.hotel_system_api.model.response.AuthenticationResponse;
import com.techgirl.hotel_system_api.repository.UserRepository;
import com.techgirl.hotel_system_api.service.auth.AuthService;
import com.techgirl.hotel_system_api.service.auth.UserService;
import com.techgirl.hotel_system_api.utils.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        try {
            return new ResponseEntity<>(authService.createUser(request), HttpStatus.OK);

        }catch (EntityExistsException e) {
            return new ResponseEntity<>("User already exist.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        }catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password.");
        }

        final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(request.getEmail());
        final String token = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setToken(token);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }

        return authenticationResponse;

    }
}
