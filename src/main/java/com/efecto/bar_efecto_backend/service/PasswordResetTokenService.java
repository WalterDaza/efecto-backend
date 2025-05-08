package com.efecto.bar_efecto_backend.service;

public interface PasswordResetTokenService {

    public void createPasswordResetToken(String email);
    public void resetPassword(String token, String newPassword);

}
