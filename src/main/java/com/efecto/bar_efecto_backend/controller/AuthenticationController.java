package com.efecto.bar_efecto_backend.controller;

import com.efecto.bar_efecto_backend.dto.AuthenticationRequest;
import com.efecto.bar_efecto_backend.dto.AuthenticationResponse;
import com.efecto.bar_efecto_backend.dto.RegisterRequest;
import com.efecto.bar_efecto_backend.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody
            @Valid
            AuthenticationRequest authenticationRequest){

        AuthenticationResponse jwtDto = authenticationService.login(authenticationRequest);

        return ResponseEntity.ok(jwtDto);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody @Valid RegisterRequest registerRequest){

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }


}
