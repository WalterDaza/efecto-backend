package com.efecto.bar_efecto_backend.service;

import com.efecto.bar_efecto_backend.dto.AuthenticationRequest;
import com.efecto.bar_efecto_backend.dto.AuthenticationResponse;
import com.efecto.bar_efecto_backend.dto.RegisterRequest;
import com.efecto.bar_efecto_backend.model.User;
import com.efecto.bar_efecto_backend.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) { //Recibe el username y el password

        //UsernamePasswordAuthenticationToken crea un objeto con el usuario y la contraseña para que Spring Security valide si son correctos.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword() //Aca se pasa el user y el pass desde la consulta AuthRequest
        );
        authenticationManager.authenticate(authToken); //Si son correctas -> La autenticación continúa.  Si son incorrectas → Se lanza una excepción y el proceso se detiene.

        User user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado ")); //se busca el usuario en la base de datos usando el username

        String jwt = jwtService.generateToken(user, generateExtraClaims(user)); // Llama al metodo generateToken() de JwtService para crear un token JWT

        return new AuthenticationResponse(jwt); //Devuelve una respuesta con el token JWT generado.
    }

    private Map<String, Object> generateExtraClaims(User user) { //Este metodo crea un mapa de información extra que se incluirá en el JWT.

        Map<String, Object> extraClaims = new HashMap<>(); //Crea un mapa vacío para almacenar los atributos adicionales del usuario.
        extraClaims.put("name", user.getName()); // Se agregan los siguientes datos al token
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());

        return extraClaims; //Devuelve el mapa con la información extra que se incluirá en el JWT.

    }

    public AuthenticationResponse register(@Valid RegisterRequest registerRequest) {
        User user = User.builder() //Se usa el patrón Builder para crear una instancia de User, permitiendo asignar valores paso a paso de manera fluida.
                .username(registerRequest.getUsername()) //Obtiene el username enviado en registerRequest y lo asigna al usuario
                .password(passwordEncoder.encode(registerRequest.getPassword())) //Codifica la contraseña utilizando passwordEncoder antes de almacenarla en la base de datos para mayor seguridad.
                .name(registerRequest.getName())
                .role(registerRequest.getRole())
                .build();

        userRepository.save(user); //Usa el userRepository (una interfaz JpaRepository) para guardar el usuario en la base de datos.

        String jwt = jwtService.generateToken(user, generateExtraClaims(user)); //Se crea el token con los datos del usuario y datos extra que se incluirán en el token (como roles, permisos, etc.).

        return new AuthenticationResponse(jwt); //Retorna como respuesta un jwt  para autenticarse en futuras solicitudes.
    }

}
