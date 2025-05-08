package com.efecto.bar_efecto_backend.serviceImpl;

import com.efecto.bar_efecto_backend.model.PasswordResetToken;
import com.efecto.bar_efecto_backend.model.User;
import com.efecto.bar_efecto_backend.repository.PasswordResetTokenRepository;
import com.efecto.bar_efecto_backend.repository.UserRepository;
import com.efecto.bar_efecto_backend.service.EmailService;
import com.efecto.bar_efecto_backend.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class PasswordResetTokenImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    @Override
    public void createPasswordResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        passwordResetTokenRepository.findByUser(user).ifPresent(passwordResetTokenRepository::delete);

        String token = UUID.randomUUID().toString();
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(30);

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpirationDate(expiration);

        passwordResetTokenRepository.save(resetToken);

        emailService.sendPasswordResetEmail(email, token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido"));

        if (resetToken.isUsed() || resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado o ya usado");
        }

        User user = resetToken.getUser();
        String hashedPassword = new BCryptPasswordEncoder().encode(newPassword);
        user.setPassword(hashedPassword);

        userRepository.save(user);

        resetToken.setUsed(true);
        passwordResetTokenRepository.save(resetToken);
    }
}
