package com.example.monolithic.users.controller;

import com.example.monolithic.model.Login;
import com.example.monolithic.model.Register;
import com.example.monolithic.model.Users;
import com.example.monolithic.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @GetMapping("/test")
    public String testApi(){
        return "TestApi works.";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Register register) {
        return ResponseEntity.ok().body(service.register(register));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        return service.authenticate(login);
    }

    @PutMapping("/{id}/{role}")
    public ResponseEntity<Users> updateRole(@PathVariable int id, @PathVariable String role){
        return service.update(id, role);
    }

}
