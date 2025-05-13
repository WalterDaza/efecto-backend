package com.efecto.bar_efecto_backend.service;

public interface PasswordResetTokenService {

    public void createPasswordResetToken(String username);
    public void resetPassword(String token, String newPassword);

}
