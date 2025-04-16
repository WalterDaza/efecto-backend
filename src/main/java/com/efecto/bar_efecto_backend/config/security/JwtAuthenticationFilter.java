package com.efecto.bar_efecto_backend.config.security;

import com.efecto.bar_efecto_backend.model.User;
import com.efecto.bar_efecto_backend.repository.UserRepository;
import com.efecto.bar_efecto_backend.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { //filterChain: Permite que la solicitud continúe hacia los siguientes filtros o el controlador.
        //1. Obtener el Header que contiene el jwt
        String authHeader = request.getHeader("Authorization"); //Se obtiene el encabezado Authorization de la solicitud HTTP.

        if(authHeader == null || !authHeader.startsWith("Bearer ")){ //Se verifica si el header es nulo o si no comienza con "Bearer "
            filterChain.doFilter(request, response); //Si el encabezado no existe, la solicitud se deja pasar sin autenticación Esto permite que solicitudes sin JWT aún puedan ser procesadas (por ejemplo, rutas públicas).
            return;
        }

        //2. Obtener el jwt desde header
        String jwt = authHeader.split(" ")[1]; //Elimina la palabra "Bearer " y extrae el token real.

        //3. Obtener el subject / username desde el jwt
        String username = jwtService.getUsernameFromToken(jwt); //Se llama a jwtService.getUsernameFromToken(jwt), un metodo que decodifica el JWT y obtiene el username (o el subject del token).

        //4. Setear un objeto Authentication dentro del SecurityContext
        User user = userRepository.findByUsername(username).get(); //Se busca en la base de datos un usuario con ese username.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, //El nombre del usuario autenticado.
                null, // No se usa una contraseña aquí porque el JWT ya lo autenticó.
                user.getAuthorities() //Lista de roles y permisos que tiene el usuario.
        );

        SecurityContextHolder.getContext().setAuthentication(authToken); //Se almacena el objeto authToken en el SecurityContextHolder, lo que significa que Spring Security reconocerá que la solicitud está autenticada.

        //5. Ejecutar el resto de filtros
        filterChain.doFilter(request, response); //Se permite que la solicitud continúe hacia los siguientes filtros o controladores. Sitodo salio bien, el usuario ya está autenticado en el sistema.
    }

}
