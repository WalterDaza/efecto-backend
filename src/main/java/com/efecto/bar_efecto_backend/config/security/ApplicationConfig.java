package com.efecto.bar_efecto_backend.config.security;

import com.efecto.bar_efecto_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    @Autowired
    private UserRepository userRepository;

    //AuthenticationManager es el componente de Spring Security que se encarga de autenticar a los usuarios.
    //Recibe como parámetro un objeto de tipo AuthenticationConfiguration, que contiene la configuración de autenticación del sistema.
    //Retorna una instancia preconfigurada de AuthenticationManager, que se encarga de verificar las credenciales de los usuarios y Permite usar la configuración de autenticación ya definida en la aplicación (como UserDetailsService y PasswordEncoder).
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); //es un proveedor de autenticación que valida credenciales contra una base de datos.
        provider.setUserDetailsService(userDetailsService());//Se configura el UserDetailsService, que busca usuarios en la base de datos.
        provider.setPasswordEncoder(passwordEncoder()); //Se configura el PasswordEncoder, que es responsable de codificar y comparar contraseñas.

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //Se usa para codificar contraseñas al registrarse y compararlas al iniciar sesión.
    }

    @Bean
    public UserDetailsService userDetailsService () { // servicio que carga los detalles del usuario desde la base de datos.
        return username ->  userRepository.findByUsername(username) //Busca el usuario por su nombre en la base de datos
                .orElseThrow(() -> new UsernameNotFoundException("User no encontrado")); //Si no encuentra el usuario, lanza una excepción
    }


}
