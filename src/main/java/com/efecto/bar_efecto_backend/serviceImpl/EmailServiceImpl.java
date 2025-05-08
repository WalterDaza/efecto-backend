package com.efecto.bar_efecto_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String token) {
        String subject = "Recuperación de contraseña - TuApp";
        String resetUrl = "http://localhost:8080/reset-password?token=" + token;
        String body = "Hola,\n\nHaz clic en el siguiente enlace para restablecer tu contraseña:\n\n"
                + resetUrl + "\n\nEste enlace expirará en 30 minutos.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
