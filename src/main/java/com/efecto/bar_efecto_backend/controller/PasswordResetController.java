package com.efecto.bar_efecto_backend.controller;

import com.efecto.bar_efecto_backend.serviceImpl.PasswordResetTokenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {
    @Autowired
    private PasswordResetTokenImpl passwordResetToken;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        passwordResetToken.createPasswordResetToken(email);
        return ResponseEntity.ok("Se ha enviado un enlace de recuperación al correo.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword) {

        passwordResetToken.resetPassword(token, newPassword);
        return ResponseEntity.ok("Contraseña actualizada correctamente.");
    }
}
