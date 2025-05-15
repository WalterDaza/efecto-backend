package com.efecto.bar_efecto_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BarEfectoBackendApplication {

	public static void main(String[] args)	 {
		SpringApplication.run(BarEfectoBackendApplication.class, args);


		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode("123456");
		System.out.println(hashedPassword);
	}

}
