package com.example.Spring_security_test.service;

import com.example.Spring_security_test.config.JwtUtils;
import com.example.Spring_security_test.model.ERole;
import com.example.Spring_security_test.model.Login;
import com.example.Spring_security_test.model.Register;
import com.example.Spring_security_test.model.Users;
import com.example.Spring_security_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public String register(Register register) {
        var user = Users.builder()
                .userName(register.getName())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .number(register.getNumber())
                .role(ERole.USER).build();
        userRepository.save(user);
        return "User Added Successfully";
    }

    public ResponseEntity<String> authenticate(Login login) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        Optional<Users> userDetails = userRepository.findByEmail(login.getEmail());
        String token = jwtUtils.generateToken(userDetails.get());
        return ResponseEntity.ok(token);
    }
}