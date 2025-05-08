package com.efecto.bar_efecto_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String token) {
        String subject = "Recuperación de contraseña - Bar Efecto";
        String body = "Hola,\n\nAcabamos de recibir una solicitud para restablecer la contraseña:\n\n"
                + "Token: "+ token + "\n\nEste token expirará en 30 minutos.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
